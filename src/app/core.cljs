(ns app.core
  (:require
   [reagent.core :as r]
   ["react-dom/client" :as ReactDOMClient]
   [app.components.test-counter.core :as test-counter]))

(defonce container (.getElementById js/document "app"))
(defonce root (.createRoot ReactDOMClient container))

(defn app []
  [:div.container.mt-5
   [:h1 "Hello, world!"]
   [test-counter/counter]])

(defn init []
  (.render root (r/as-element [app])))

(defn ^:after-load on-reload []
  (init))
