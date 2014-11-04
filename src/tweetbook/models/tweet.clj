(ns tweetbook.models.tweet
  (:require [tweetbook.models.config :as config])
  (:use [twitter.oauth]
        [twitter.callbacks]
        [twitter.callbacks.handlers]
        [twitter.api.restful]
        [overtone.at-at]
        [tweetbook.models.db]))

(def thread-pool (mk-pool))
(def tweet-interval config/tweet-interval)
(def tweetbot-creds
  (let [creds config/oauth-settings]
    (make-oauth-creds (creds :consumer-key)
                      (creds :consumer-secret)
                      (creds :access-token)
                      (creds :access-token-secret))))

(defn tweet [msg]
  (statuses-update :oauth-creds tweetbot-creds :params {:status msg}))

(defn tweet-random []
  (tweet (random-msg)))

;; (->> (followers-list :oauth-creds tweetbot-creds :params {:screen-name "ntalbs" :count 5000})
;;      :body :users
;;      (map (fn [e] [(:name e) (:screen_name e)])))
