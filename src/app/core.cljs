(ns app.core
  (:require
   [reagent.core :as r]
   ["react-dom/client" :as ReactDOMClient]
   [app.routes :as routes]
   [app.components.test-counter.core :as test-counter]
   [app.components.navbar.core :refer [navbar]]
   [app.components.footer.core :refer [footer]]))

(defonce container (.getElementById js/document "app"))
(defonce root (.createRoot ReactDOMClient container))

(defn app []
  (let [{:keys [data]} @routes/current-route]
    (if-let [view (:view data)]
      [:<>
       [navbar]
       [:div.container.mt-5.main-content
        [view]
        [test-counter/counter]]
       [footer]]
      [:div "Page not found"])))

(defn init []
  (routes/init-routes!)
  (.render root (r/as-element [app])))

(defn ^:after-load on-reload []
  (init))
