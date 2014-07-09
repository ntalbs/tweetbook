(ns tweetbook.routes.auth
  (:require [compojure.core :refer [defroutes GET POST]]
            [tweetbook.views.layout :as layout]
            [hiccup.form :refer [form-to label text-field password-field submit-button]]
            [noir.response :refer [redirect]]
            [noir.session :as session]))

(defn control [field name text]
  (list (label name text)
        (field name)
        [:br]))

(defn login-page []
  (layout/common (form-to [:post "/login"]
                          (control text-field :id "screen name")
                          (control password-field :pass "password")
                          (submit-button "login"))))

(defroutes auth-routes
  (GET "/login" [] (login-page))
  (POST "/login" [id pass]
        (session/put! :user id)
        (redirect "/")))
