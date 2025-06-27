(ns app.core
  (:require [reagent.core :as r]
            ["react-dom/client" :as ReactDOMClient]))

(defn app []
  [:div
   [:h1 "Hello from Reagent + React 18!"]
   [:p "Edit `core.cljs` and save to see changes."]])

(defn init []
  (let [container (.getElementById js/document "app")
        root (.createRoot ReactDOMClient container)]
    (.render root (r/as-element [app]))))
