(ns ^:figwheel-always {{name}}.core
    (:require [rum.core :as rum]
              [cljs.reader :as reader]
              [clojure.set :refer (intersection)]
              [cljsjs.react]
              [jayq.core :refer ($)]
              )
    (:require-macros [jayq.macros :refer [ready]])
)

(enable-console-print!)

;;;
;; define game state once so it doesn't re-initialise on reload.
;; figwheel counter is a placeholder for any state affected by figwheel live reload events
;;;
(defonce game (atom {:title "Hello {{name}}"
                     :__figwheel_counter 0}))

(defn el [id] (.getElementById js/document id))

;;;
;; Sample jQuery usage:
;;  Attach the jQuery plugin only after React has mounted the selected element and jQuery has loaded.
;;;
(defn home-did-mount
  "attach DataTable plugin to the example table. Adds search, filter, sorts, and pagination."
  [state]
  (ready
   (.DataTable ($ :#example)))
  state)

;; data-table mixin - adds jQuery DataTable code on jQuery ready
(def data-table {:did-mount home-did-mount})

(rum/defc home < rum/reactive data-table []
  [:div.row
    [:div.col-md-6
     [:table#example.table.table-striped.table-bordered {:cell-spacing "0" :width "100%"}
      [:thead
       [:tr [:th "Name"]
        [:th "Age"]]]
      [:tbody
       [:tr [:td "Matthew"]
        [:td "26"]]
       [:tr [:td "Anna"]
        [:td "24"]]
       [:tr [:td "Michelle"]
        [:td "42"]]
       [:tr [:td "Frank"]
        [:td "46"]]]]]])

;;
;; Put the app/game in here
;;
(rum/defc game-container < rum/reactive []
  [:div#box
   [:h1 (:title (rum/react game))]
   (home)])

;;
;; mount main component on html game element
;;
(rum/mount (game-container) (el "game"))

;;
;; optionally do something on game reload
;;
(defn on-js-reload []
  (swap! game update-in [:__figwheel_counter] inc))
