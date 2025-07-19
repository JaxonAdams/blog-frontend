(ns app.core
  (:require
   [reagent.core :as r]
   ["react-dom/client" :as ReactDOMClient]
   [app.routes :as routes]
   [app.components.navbar.core :refer [navbar]]
   [app.components.footer.core :refer [footer]]))

(defonce container (.getElementById js/document "app"))
(defonce root (.createRoot ReactDOMClient container))

(defn app []
  (let [{:keys [data] :as match} @routes/current-route
        view (:view data)]
    (if view
      [:<>
       [navbar]
       [:div.container.mt-5.main-content
        [view match]
        [footer]]]
      [:div "Page not found"])))

(defn init []
  (routes/init-routes!)
  (.render root (r/as-element [app])))

(defn ^:after-load on-reload []
  (init))
