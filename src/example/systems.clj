(ns example.systems
  (:require 
   [system.core :refer [defsystem]]
   (system.components
    [http-kit :refer [new-web-server]]
    [h2 :refer [new-h2-database DEFAULT-MEM-SPEC DEFAULT-DB-SPEC]])
   [example.handler :refer [app]]
   [example.db :refer [create-table!]]
   [environ.core :refer [env]]))

(defn select-database [env]
  (let [dbs {"default-mem-spec" DEFAULT-MEM-SPEC
             "default-db-spec" DEFAULT-DB-SPEC}]
    (get dbs (env :db) DEFAULT-MEM-SPEC)))

(defsystem base-system
  [:db (new-h2-database (select-database env) #(create-table! {} {:connection %}))
   :web (new-web-server (Integer. (env :http-port)) app)])

