(ns tweetbook.routes.home
  (:require [compojure.core :refer :all]
            [liberator.core :refer [defresource resource request-method-in]]
            [cheshire.core :refer [generate-string]]
            [noir.io :as io]
            [noir.session :as sesson]
            [clojure.java.io :refer [file]]
            [tweetbook.models.tweet :refer [tweet]]
            [tweetbook.models.db :as db]))

(defn keywordify [m]
  (into {} (for [[k v] m] [(keyword k) v])))

(defresource home
  :allowed-methods [:get]
  :available-media-types ["text/html"]

  :exists?
  (fn [_]
    [(io/get-resource "/index.html")
     {::file (file (str (io/resource-path) "/index.html"))}])

  :handle-ok
  (fn [_] (clojure.java.io/input-stream (io/get-resource "/index.html")))

  :last-modified
  (fn [_] (.lastModified (file (str (io/resource-path) "/index.html")))))

(defresource add-message
  :allowed-methods [:post]
  :available-media-types ["application/json"]
  :post!
  (fn [context]
    (let [{:keys [msg src tweet-immediately]} (keywordify (get-in context [:request :form-params]))]
      (db/insert-msg {:msg msg, :src src})
      (if (= "on" tweet-immediately)
        (tweet (str msg "\n" src)))))
  :handle-created
  (fn [_] (generate-string {:result "OK"})))

(defroutes home-routes
  (ANY "/" [] home)
  (ANY "/addmesg" [] add-message))
