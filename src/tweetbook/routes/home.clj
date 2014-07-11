(ns tweetbook.routes.home
  (:require [compojure.core :refer :all]
            [liberator.core :refer [defresource resource request-method-in]]
            [cheshire.core :refer [generate-string]]
            [noir.io :as io]
            [clojure.java.io :refer [file]]
            [tweetbook.models.db :as db]))

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
    (let [params (get-in context [:request :form-params])]
      (db/insert-msg {:msg (params "msg"), :src (params "src")})
      (if (= (params "tweet-immediately") "on") (println "tweet-immediately on"))))
  :handle-created
  (fn [_] (generate-string {:result "OK"})))

(defroutes home-routes
  (ANY "/" [] home)
  (ANY "/addmesg" [] add-message))
