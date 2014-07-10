(ns tweetbook.routes.home
  (:require [compojure.core :refer :all]
            [liberator.core :refer [defresource resource request-method-in]]
            [noir.io :as io]
            [clojure.java.io :refer [file]]))

(defresource home
  :available-media-types ["text/html"]

  :exists?
  (fn [_]
    [(io/get-resource "/index.html")
     {::file (file (str (io/resource-path) "/index.html"))}])

  :handle-ok
  (fn [{{{resource :resource} :route-params} :request}]
    (clojure.java.io/input-stream (io/get-resource "/index.html")))

  :last-modified
  (fn [{{{resource :resource} :route-params} :request}]
    (.lastModified (file (str (io/resource-path) "/index.html")))))

(defroutes home-routes
  (ANY "/" [] home))
