(ns tweetbook.routes.home
  (:require [compojure.core :refer :all]
            [hiccup.form :refer :all]
            [tweetbook.views.layout :as layout]))

(defn show-form []
  (form-to [:post "/"]
           (text-area {:rows 5 :cols 50} "msg") [:br]
           (text-field "src") [:br]
           [:p#count "0"]))

(defn home []
  (layout/common (show-form)))

(defroutes home-routes
  (GET "/" [] (home)))
