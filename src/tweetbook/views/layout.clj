(ns tweetbook.views.layout
  (:require [hiccup.page :refer [html5 include-css include-js]]))

(defn common [& body]
  (html5
    [:head
     [:title "Welcome to tweetbook"]
     (include-css "/css/screen.css")
     (include-css "//maxcdn.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css")
     (include-js "//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.1/jquery.min.js")
     (include-js "//maxcdn.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js")
     (include-js "/js/count.js")]
    [:body body]))
