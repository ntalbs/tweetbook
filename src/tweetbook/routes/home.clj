(ns tweetbook.routes.home
  (:require [compojure.core :refer :all]
            [liberator.core :refer [defresource resource]]
            [cheshire.core :refer [generate-string]]
            [noir.session :as session]
            [clojure.java.io :as io]
            [tweetbook.models.config :as config]
            [tweetbook.models.tweet :refer [tweet]]
            [tweetbook.models.db :as db]))

(def page-file (io/file (io/resource "tweetbook/routes/tweet.html")))

(defn keywordify [m] (into {} (for [[k v] m] [(keyword k) v])))

(defn redirect-to-login-page [_]
  (liberator.representation/ring-response {:status 302, :headers {"Location" "/login"}, :body ""}))

(defresource home
  :allowed-methods [:get]
  :available-media-types ["text/html"]
  :exists? (fn [_] [(.getAbsolutePath page-file) {::file page-file}])
  :last-modified (fn [_] (.lastModified page-file))
  :authorized? (fn [_] (= (session/get :id) (config/user :id)))
  :handle-ok (fn [_] (io/input-stream page-file))
  :handle-unauthorized redirect-to-login-page)

(defresource add-message
  :allowed-methods [:post]
  :available-media-types ["application/json"]
  :post!
  (fn [context]
    (let [{:keys [msg src tweet-immediately]} (keywordify (get-in context [:request :form-params]))]
      (db/insert-msg {:msg msg, :src src})
      (if (= "on" tweet-immediately)
        (tweet (str msg "\n" src)))))
  :handle-created (fn [_] (generate-string {:result "OK"}))
  :authorized? (fn [_] (= (session/get :id) (config/user :id)))
  :handle-unauthorized redirect-to-login-page)

(defroutes home-routes
  (ANY "/" [] home)
  (ANY "/addmesg" [] add-message))
