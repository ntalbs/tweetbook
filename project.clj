(defproject tweetbook "0.1.0"
  :description "Tweet snippets from books"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.1"]
                 [liberator "0.12.2"]
                 [cheshire "5.4.0"]
                 [ring-server "0.3.1"]
                 [lib-noir "0.9.5"]
                 [twitter-api "0.7.7"]
                 [overtone/at-at "1.2.0"]
                 [com.novemberain/monger "2.0.0"]]
  :plugins [[lein-ring "0.8.10"]]
  :ring {:handler tweetbook.handler/app
         :init tweetbook.handler/init
         :destroy tweetbook.handler/destroy}
  :aot :all
  :profiles
  {:production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}}
   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.2.1"]]}})
