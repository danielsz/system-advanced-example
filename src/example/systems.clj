(ns example.systems
  (:require 
   [system.core :refer [defsystem]]
   (system.components
    [http-kit :refer [new-web-server]]
    [h2 :refer [new-h2-database DEFAULT-MEM-SPEC DEFAULT-DB-SPEC]])
   [example.handler :refer [app]]
   [example.db :refer [create-table!]]
   [environ.core :refer [env]]))

(defsystem dev-system
  [:db (new-h2-database DEFAULT-MEM-SPEC #(create-table! {} {:connection %}))
   :web (new-web-server (Integer. (env :http-port)) app)])

(defsystem prod-system
  [:db (new-h2-database DEFAULT-DB-SPEC)
   :web (new-web-server (Integer. (env :http-port)) app)])
