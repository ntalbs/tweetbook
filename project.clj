(defproject tweetbook "0.1.0"
  :description "Tweet snippets from books"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.0"]
                 [liberator "0.14.1"]
                 [cheshire "5.5.0"]
                 [ring-server "0.4.0"]
                 [lib-noir "0.9.9"]
                 [twitter-api "0.7.8"]
                 [clj-http "2.1.0"]
                 [overtone/at-at "1.2.0"]
                 [com.novemberain/monger "3.0.1"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler tweetbook.handler/app
         :init tweetbook.handler/init
         :destroy tweetbook.handler/destroy}
  :aot :all
  :profiles
  {:production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}}
   :dev
   {:dependencies [[ring/ring-mock "0.3.0"] [ring/ring-devel "1.4.0"]]}})
