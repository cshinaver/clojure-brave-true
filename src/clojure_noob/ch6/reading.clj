(ns clojure-noob.ch6.reading)

(def great-books ["East of Eden" "The Glass Bead Game"])

(def second-books #'clojure-noob.ch6.reading/great-books)


; Process of creating var, saving thing in memory of var,
; binding symbol to var is interning
(ns-interns *ns*)

(deref #'clojure-noob.ch6.reading/great-books)

; Clojure eval of symbol
(deref (get (ns-map *ns*) 'great-books))

(create-ns 'derpin)

