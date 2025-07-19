(ns app.components.footer.core)

(defn footer []
  [:footer.text-center.mt-5.py-4.text-muted
   [:div.container
    "© 2025 Jaxon Adams | "
    [:a.text-reset {:href "https://github.com/JaxonAdams" :target "_blank"} "GitHub"]
    " · "
    [:a.text-reset {:href "https://www.linkedin.com/in/jaxon-adams-ba5743229/" :target "_blank"} "LinkedIn"]]])
