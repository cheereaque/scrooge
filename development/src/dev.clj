(ns dev
  (:refer-clojure :exclude [test])
  (:require
    [clojure.tools.namespace.repl :refer [set-refresh-dirs]]
    [duct.core :as duct]
    [integrant.core :as ig]
    [integrant.repl :as ig-repl]))

(set-refresh-dirs "bases/api/src")
(duct/load-hierarchy)

(defn read-config
  []
  (let [config (duct/read-config (duct/resource "api/base.edn"))]
    (ig/load-namespaces config)
    config))

(defn go
  []
  (ig-repl/go))

(defn halt
  []
  (ig-repl/halt))

(defn reset
  []
  (ig-repl/reset))

(def profiles
  [:duct.profile/dev
   :duct.profile/local])

(when (duct/resource "dev.edn")
  (load "dev"))

(ig-repl/set-prep! #(duct/prep-config (read-config) profiles))

(comment
  (require '[integrant.repl.state :as ig-state])
  (keys ig-state/system)
  (reset))

