(set-env!
 :source-paths   #{"src"}
 :resource-paths #{"resources"}
 :dependencies '[[adzerk/boot-reload "0.4.13" :scope "test"]
                 [yesql "0.5.3"]
                 [org.clojure/java.jdbc "0.6.1"]
                 [com.h2database/h2 "1.4.193"]
                 [environ"1.1.0"]
                 [boot-environ "1.1.0"]
                 [org.clojure/clojure "1.8.0"]
                 [org.danielsz/system "0.3.2-SNAPSHOT"]
                 [ring/ring-defaults "0.2.0"]
                 [ring-middleware-format "0.7.0"]
                 [http-kit "2.1.19"]
                 [compojure "1.5.0"]
                 [hiccup "1.0.5"]
                 [clj-http "3.4.1"]
                 [org.clojure/data.json "0.2.6"]])

(require
 '[adzerk.boot-reload    :refer [reload]]
 '[example.systems :refer [dev-system prod-system]]
 '[environ.boot :refer [environ]] 
 '[system.boot :refer [system run]])

(deftask dev
  "Run a restartable system in the Repl"
  []
  (comp
   (environ :env {:http-port "3025"
                  :imdb-key "348b843dca6130f34597bea34cb95701"})
   (watch :verbose true)
   (system :sys #'dev-system :auto true :files ["handler.clj"])
   (repl :server true)))


(deftask dev-run
  "Run a restartable system in the Repl"
  []
  (comp
   (environ :env {:http-port "3025"
                  :imdb-key "348b843dca6130f34597bea34cb95701"})
   (run :main-namespace "example.core" :arguments [#'dev-system])
   (wait)))

(deftask prod-run
  "Run a restartable system in the Repl"
  []
  (comp
   (environ :env {:http-port "8008"
                  :imdb-key "348b843dca6130f34597bea34cb95701"})
   (run :main-namespace "example.core" :arguments [#'prod-system])
   (wait)))
