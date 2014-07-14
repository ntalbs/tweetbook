(ns tweetbook.models.config)

(def db-uri "mongodb-uri")

(def oauth-settings
  {:consumer-key "consumer-key"
   :consumer-secret "consumer-secret"
   :access-token "access-token"
   :access-token-secret "access-token-secret"})

(def tweet-interval (* 1000 60 60 2))
