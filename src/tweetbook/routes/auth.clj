(ns tweetbook.routes.auth
  (:require [compojure.core :refer [defroutes GET POST]]
            [tweetbook.models.config :as config]
            [noir.response :refer [redirect]]
            [noir.session :as session]
            [noir.util.crypt :as crypt]))

(defn handle-login [id pw]
  (if (and (= id (config/user :id)) (crypt/compare pw (config/user :pw-hash)))
    (do
      (session/put! :id id)
      (redirect "/"))))

(defn handle-logout []
  (session/clear!)
  (redirect "/login"))

(defroutes auth-routes
  (GET "/login" [] (redirect "/login.html"))
  (POST "/login" [id pw] (handle-login id pw))
  (POST "/logout" [] (handle-logout)))
