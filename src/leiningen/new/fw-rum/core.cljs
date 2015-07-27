(ns ^:figwheel-always {{kaxo}} .core
    (:require [rum :as r]
              [cljs.reader :as reader]
              [clojure.set :refer (intersection)]
              [cljsjs.react]
              ))

(enable-console-print!)

;; define game state once so it doesn't re-initialise on reload
(defonce game (atom {:title "Hello {{name}}"}))

(defn el [id] (.getElementById js/document id))

;;
;; Put the app/game in here
;;
(r/defc game-container < r/reactive [] 
  [:div#box
   [:h1 (:title (r/react game))]])


;;
;; mount main component on html game element
;;
(r/mount (game-container) (el "game"))


;;
;; optionally do something on game reload
;;
(defn on-js-reload []
  (swap! game update-in [:__figwheel_counter] inc))
