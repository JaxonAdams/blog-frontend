(ns app.components.footer.core)

(defn footer []
  [:footer.text-center.mt-5.py-4.text-muted
   [:div.container
    "© 2025 Jaxon Adams · Built with "
    [:a.text-decoration-none.text-reset {:href "https://clojurescript.org/" :target "_blank"} "ClojureScript"]
    " and "
    [:a.text-decoration-none.text-reset {:href "https://reagent-project.github.io/" :target "_blank"} "Reagent"]]])
