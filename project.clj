(defproject tweetbook "0.1.0"
  :description "Tweet snippets from books"
  :url "http://{app-engine-id}.appspot.com"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.6"]
                 [hiccup "1.0.5"]
                 [ring-server "0.3.1"]
                 [lib-noir "0.8.4"]
                 [twitter-api "0.7.5"]
                 [com.novemberain/monger "2.0.0-rc1"]
                 ]
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
