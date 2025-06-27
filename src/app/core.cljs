(ns app.core
  (:require
   [reagent.core :as r]
   ["react-dom/client" :as ReactDOMClient]
   [app.components.test-counter.core :as test-counter]
   [app.components.navbar.core :refer [navbar]]
   [app.components.blog.main.core :refer [blog-main]]
   [app.components.footer.core :refer [footer]]))

(defonce container (.getElementById js/document "app"))
(defonce root (.createRoot ReactDOMClient container))

(defn app []
  [:<>
   [navbar]
   [:div.container.mt-5.main-content
    [blog-main]
    [test-counter/counter]]
   [footer]])

(defn init []
  (.render root (r/as-element [app])))

(defn ^:after-load on-reload []
  (init))
