(ns app.core
  (:require [reagent.core :as r]
            ["react-dom/client" :as ReactDOMClient]))

(defonce container (.getElementById js/document "app"))
(defonce root (.createRoot ReactDOMClient container))

(defn app []
  [:div
   [:h1 "Hello, world!"]
   [:p "Edit `core.cljs` and save to see changes."]])

(defn init []
  (.render root (r/as-element [app])))

(defn ^:after-load on-reload []
  (init))
