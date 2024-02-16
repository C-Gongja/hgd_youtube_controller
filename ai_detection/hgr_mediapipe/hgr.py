import csv
from collections import deque

import cv2 as cv
import mediapipe as mp

from mod_hgr_helper import get_args
from mod_hgr_helper import handGestureRecogSingleton

import os

def hgr():
	# Argument parsing #################################################################
	args = get_args()

	cap_device = args.device
	cap_width = args.width
	cap_height = args.height

	use_static_image_mode = args.use_static_image_mode
	min_detection_confidence = args.min_detection_confidence
	min_tracking_confidence = args.min_tracking_confidence

	use_brect = True

	# Camera preparation ###############################################################
	cap = cv.VideoCapture(cap_device)
	cap.set(cv.CAP_PROP_FRAME_WIDTH, cap_width)
	cap.set(cv.CAP_PROP_FRAME_HEIGHT, cap_height)

	# Model load #############################################################
	mp_hands = mp.solutions.hands
	hands = mp_hands.Hands(
		static_image_mode=use_static_image_mode,
		max_num_hands=2,
		min_detection_confidence=min_detection_confidence,
		min_tracking_confidence=min_tracking_confidence,
	)

	script_dir = os.path.dirname(os.path.abspath(__file__))
	csv_path = os.path.join(script_dir, 'model/keypoint_classifier/keypoint_classifier_label.csv')

	script_point_dir = os.path.dirname(os.path.abspath(__file__))
	csv_point_path = os.path.join(script_point_dir, 'model/point_history_classifier/point_history_classifier_label.csv')

	# Read labels ###########################################################
	with open(csv_path, encoding='utf-8-sig') as f:
		keypoint_classifier_labels = csv.reader(f)
		keypoint_classifier_labels = [
			row[0] for row in keypoint_classifier_labels
		]
	with open(csv_point_path, encoding='utf-8-sig') as f:
		point_history_classifier_labels = csv.reader(f)
		point_history_classifier_labels = [
			row[0] for row in point_history_classifier_labels
		]

	# Coordinate history #################################################################
	history_length = 16
	point_history = deque(maxlen=history_length)

	# Finger gesture history ################################################
	finger_gesture_history = deque(maxlen=history_length)

	mode = 0
	hgr = handGestureRecogSingleton(hands, keypoint_classifier_labels, point_history_classifier_labels)
	pre_hgr_sign = ""
	while True:
		hgr_sign = handGestureRecogSingleton.handGestureRecognition(hgr, cap, point_history, finger_gesture_history, mode, use_brect)
		if hgr_sign[1] == False:
			break
		
		if pre_hgr_sign != hgr_sign and hgr_sign[1] != "":
			print(hgr_sign, flush=True)
			pre_hgr_sign = hgr_sign

	print("terminate")
	cap.release()
	cv.destroyAllWindows()

if __name__ == '__main__':
	hgr()

