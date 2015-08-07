(set-env!
 :source-paths   #{"src"}
 :resource-paths #{"resources"}
 :dependencies '[[adzerk/boot-reload    "0.3.1"      :scope "test"]
                 [org.clojure/java.jdbc "0.4.1"]
                 [com.h2database/h2 "1.4.187"]
                 [environ"1.0.0"]
                 [boot-environ "1.0.0-RC1"]
                 ;[danielsz/boot-environ "0.0.4"]
                 [org.clojure/clojure "1.7.0"]
                 [org.danielsz/system "0.1.8"]
                 [ring/ring-defaults "0.1.5"]
                 [http-kit "2.1.19"]
                 [compojure "1.4.0"]
                 [org.danielsz/system "0.1.8"]
                 [hiccup "1.0.5"]
                 [clj-http "2.0.0"]
                 [yesql "0.4.2"]
                 [org.clojure/data.json "0.2.6"]])

(require
 '[adzerk.boot-reload    :refer [reload]]
 '[reloaded.repl :refer [init start stop go reset]]
 '[example.systems :refer [dev-system prod-system]]
 '[environ.boot :refer [environ]] ;[danielsz.boot-environ :refer [environ]]
 '[system.boot :refer [system run]])

(deftask dev
  "Run a restartable system in the Repl"
  []
  (comp
   (environ :env {:http-port "3025"})
   (watch :verbose true)
   (system :sys #'dev-system :auto-start true :hot-reload true :files ["handler.clj"])
   ;(reload)
   (repl :server true)))
