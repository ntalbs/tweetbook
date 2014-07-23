(ns tweetbook.handler
  (:require [compojure.core :refer [defroutes routes]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [noir.session :as session]
            [ring.middleware.session.memory :refer [memory-store]]
            [overtone.at-at :refer [every mk-pool]]
            [tweetbook.models.config :refer [tweet-interval]]
            [tweetbook.models.tweet :refer [tweet-random]]
            [tweetbook.routes.auth :refer [auth-routes]]
            [tweetbook.routes.home :refer [home-routes]]))

(def thread-pool (mk-pool))

(defn init []
  (do
    (println "tweetbook is starting")
    (every tweet-interval #(tweet-random) thread-pool)))

(defn destroy []
  (println "tweetbook is shutting down"))

(defroutes app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (routes auth-routes home-routes app-routes)
      (handler/site)
      (session/wrap-noir-session {:store (memory-store)})))
