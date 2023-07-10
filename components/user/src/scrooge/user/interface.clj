(ns scrooge.user.interface
  (:refer-clojure :exclude [test])
  (:require
    [clojure.java.io :as io]
    [clojure.tools.namespace.repl :as ns-repl]
    [duct.core :as duct]
    [integrant.repl :as ig-repl]))

(duct/load-hierarchy)

(defn read-config
  []
  (duct/read-config (io/resource "user/config.edn")))

(ns-repl/set-refresh-dirs "src" "resources")

(defn dev
  []
  (let [profiles [:duct.profile/dev]]
    (-> (read-config)
        (duct/prep-config profiles)
        (ig-repl/set-prep!)))
  :loaded)

(dev)
