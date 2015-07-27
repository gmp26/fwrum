(ns leiningen.new.fw-rum
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "fw-rum"))


(defn fw-rum
  [name]
  (do
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
               [".gitignore" (render "gitignore" data)]))))
