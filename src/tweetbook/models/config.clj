(ns tweetbook.models.config)

(def db-uri (System/getenv "TWEETBOOK_DB_URI"))

(def oauth-settings
  {:consumer-key (System/getenv "TWEETBOOK_TWITTER_CONSUMER_KEY"),
   :consumer-secret (System/getenv "TWEETBOOK_TWITTER_CONSUMER_SECRET"),
   :access-token (System/getenv "TWEETBOOK_TWITTER_ACCESS_TOKEN"),
   :access-token-secret (System/getenv "TWEETBOOK_TWITTER_ACCESS_TOKEN_SECRET")})

(def tweet-interval (* 1000 60 60 2))
(def snapshot-interval (* 1000 60 60 24))

(def user
  {:id (System/getenv "TWEETBOOK_ID")
   :pw-hash (System/getenv "TWEETBOOK_PW_HASH")})
