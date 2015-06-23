(ns image-resizer-web.service
  (:require [image-resizer.resize :refer :all]
            [image-resizer.core :as resizer]
            [clojure.java.io :as io]))

(defn serve-image [image]
  (let [out-stream (java.io.ByteArrayOutputStream.)]
    (do
      (javax.imageio.ImageIO/write image "png" out-stream)
      (java.io.ByteArrayInputStream.
       (.toByteArray out-stream)))))

(defn image-resize [image resize-fn params]
  (let [filename (str "resources/public/" image)]
    (-> (io/file filename)
        (resize-fn params)
        (serve-image))))

(defn auto-resize [image {:keys [width height]}]
  (let [width-px  (Integer/parseInt width)
        height-px (Integer/parseInt height)]
    (resizer/resize image width-px height-px)))

(defn resize-to-width [image width]
  (let [width-px (Integer/parseInt width)]
    (resizer/resize-to-width image width-px)))

(defn resize-to-height [image height]
  (let [height-px (Integer/parseInt height)]
    (resizer/resize-to-height image height-px)))

(defn force-resize [image {:keys [width height]}]
  (let [width-px  (Integer/parseInt width)
        height-px (Integer/parseInt height)]
    (resizer/force-resize image width-px height-px)))

