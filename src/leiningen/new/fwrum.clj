(ns leiningen.new.fwrum
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "fwrum"))


(defn fwrum
  "Generates a figwheel/rum project supporting small apps or games.
  lein new fw-rum shiny-new-project"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)
              }]
    (main/info "Generating fresh 'lein new' fw-rum project.")
    (->files data
             ["README.md" (render "README.md" data)]               
             ["project.clj" (render "project.clj" data)]
             ["src/{{sanitized}}/core.cljs" (render "core.cljs" data)]
             ["resources/public/index.html" (render "index.html" data)]
             ["resources/public/css/style.css" (render "style.css" data)]
             [".gitignore" (render "gitignore" data)])))
