package com.example.ifreehandsoftkeyboard;

import java.io.IOException;
import java.io.InputStream;
import java.util.BitSet;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends InputMethodService implements
		OnKeyboardActionListener {
	public static final int INVALID_POINTER_ID = -1;
	View keyboardLayout;
	TableLayout tblLayout;
	TextView txtView;

	BitSet bitSet = new BitSet(10);
	String lPattern = "L", rPattern = "R";
	Boolean flag = false, shifted = false, fullKeyboard = true;
	int pointerCount = 1;
	private long mLastShiftTime;

	int touchID = INVALID_POINTER_ID;
	PointF touchLocation = new PointF();
	boolean touchLetters = false;

	char char1 = 0;
	char char2 = 0;
	char char3 = 0;

	//char[][] arrayOfChar;
	char[] arrayOfChar1;
	char[] arrayOfChar2;
	char[] arrayOfChar3;
	char[] arrayOfChar4;
	char[] arrayOfChar5;
	char[] arrayOfChar6;
		
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		char1 = 0;
		char2 = 0;
		char3 = 0;
		
		//arrayOfChar = new char[6][];
		arrayOfChar1 = new char[24];
		arrayOfChar1[0] = 'a';
		arrayOfChar1[1] = 'e';
		arrayOfChar1[2] = 'i';
		arrayOfChar1[3] = 'o';
		arrayOfChar1[4] = 'u';
		arrayOfChar1[5] = 'y';
		arrayOfChar1[6] = 'â';
		arrayOfChar1[7] = 'ê';
		arrayOfChar1[8] = 'ô';
		arrayOfChar1[9] = 'ă';
		arrayOfChar1[10] = 'ơ';
		arrayOfChar1[11] = 'ư';
		arrayOfChar1[12] = 'A';
		arrayOfChar1[13] = 'E';
		arrayOfChar1[14] = 'I';
		arrayOfChar1[15] = 'O';
		arrayOfChar1[16] = 'U';
		arrayOfChar1[17] = 'Y';
		arrayOfChar1[18] = 'Â';
		arrayOfChar1[19] = 'Ê';
		arrayOfChar1[20] = 'Ô';
		arrayOfChar1[21] = 'Ă';
		arrayOfChar1[22] = 'Ơ';
		arrayOfChar1[23] = 'Ư';
		arrayOfChar2 = new char[24];
		arrayOfChar2[0] = 'á';
		arrayOfChar2[1] = 'é';
		arrayOfChar2[2] = 'í';
		arrayOfChar2[3] = 'ó';
		arrayOfChar2[4] = 'ú';
		arrayOfChar2[5] = 'ý';
		arrayOfChar2[6] = 'ấ';
		arrayOfChar2[7] = 'ế';
		arrayOfChar2[8] = 'ố';
		arrayOfChar2[9] = 'ắ';
		arrayOfChar2[10] = 'ớ';
		arrayOfChar2[11] = 'ứ';
		arrayOfChar2[12] = 'Á';
		arrayOfChar2[13] = 'É';
		arrayOfChar2[14] = 'Í';
		arrayOfChar2[15] = 'Ó';
		arrayOfChar2[16] = 'Ú';
		arrayOfChar2[17] = 'Ý';
		arrayOfChar2[18] = 'Ấ';
		arrayOfChar2[19] = 'Ế';
		arrayOfChar2[20] = 'Ố';
		arrayOfChar2[21] = 'Ắ';
		arrayOfChar2[22] = 'Ớ';
		arrayOfChar2[23] = 'Ứ';
		arrayOfChar3 = new char[24];
		arrayOfChar3[0] = 'à';
		arrayOfChar3[1] = 'è';
		arrayOfChar3[2] = 'ì';
		arrayOfChar3[3] = 'ò';
		arrayOfChar3[4] = 'ù';
		arrayOfChar3[5] = 'ỳ';
		arrayOfChar3[6] = 'ầ';
		arrayOfChar3[7] = 'ề';
		arrayOfChar3[8] = 'ồ';
		arrayOfChar3[9] = 'ằ';
		arrayOfChar3[10] = 'ờ';
		arrayOfChar3[11] = 'ừ';
		arrayOfChar3[12] = 'À';
		arrayOfChar3[13] = 'È';
		arrayOfChar3[14] = 'Ì';
		arrayOfChar3[15] = 'Ò';
		arrayOfChar3[16] = 'Ù';
		arrayOfChar3[17] = 'Ỳ';
		arrayOfChar3[18] = 'Ầ';
		arrayOfChar3[19] = 'Ề';
		arrayOfChar3[20] = 'Ồ';
		arrayOfChar3[21] = 'Ằ';
		arrayOfChar3[22] = 'Ờ';
		arrayOfChar3[23] = 'Ừ';
		arrayOfChar4 = new char[24];
		arrayOfChar4[0] = 'ả';
		arrayOfChar4[1] = 'ẻ';
		arrayOfChar4[2] = 'ỉ';
		arrayOfChar4[3] = 'ỏ';
		arrayOfChar4[4] = 'ủ';
		arrayOfChar4[5] = 'ỷ';
		arrayOfChar4[6] = 'ẩ';
		arrayOfChar4[7] = 'ể';
		arrayOfChar4[8] = 'ổ';
		arrayOfChar4[9] = 'ẳ';
		arrayOfChar4[10] = 'ở';
		arrayOfChar4[11] = 'ử';
		arrayOfChar4[12] = 'Ả';
		arrayOfChar4[13] = 'Ẻ';
		arrayOfChar4[14] = 'Ỉ';
		arrayOfChar4[15] = 'Ỏ';
		arrayOfChar4[16] = 'Ủ';
		arrayOfChar4[17] = 'Ỷ';
		arrayOfChar4[18] = 'Ẩ';
		arrayOfChar4[19] = 'Ể';
		arrayOfChar4[20] = 'Ổ';
		arrayOfChar4[21] = 'Ẳ';
		arrayOfChar4[22] = 'Ở';
		arrayOfChar4[23] = 'Ử';
		arrayOfChar5 = new char[24];
		arrayOfChar5[0] = 'ã';
		arrayOfChar5[1] = 'ẽ';
		arrayOfChar5[2] = 'ĩ';
		arrayOfChar5[3] = 'õ';
		arrayOfChar5[4] = 'ũ';
		arrayOfChar5[5] = 'ỹ';
		arrayOfChar5[6] = 'ẫ';
		arrayOfChar5[7] = 'ễ';
		arrayOfChar5[8] = 'ỗ';
		arrayOfChar5[9] = 'ẵ';
		arrayOfChar5[10] = 'ỡ';
		arrayOfChar5[11] = 'ữ';
		arrayOfChar5[12] = 'Ã';
		arrayOfChar5[13] = 'Ẽ';
		arrayOfChar5[14] = 'Ĩ';
		arrayOfChar5[15] = 'Õ';
		arrayOfChar5[16] = 'Ũ';
		arrayOfChar5[17] = 'Ỹ';
		arrayOfChar5[18] = 'Ẫ';
		arrayOfChar5[19] = 'Ễ';
		arrayOfChar5[20] = 'Ỗ';
		arrayOfChar5[21] = 'Ẵ';
		arrayOfChar5[22] = 'Ỡ';
		arrayOfChar5[23] = 'Ữ';
		arrayOfChar6 = new char[24];
		arrayOfChar6[0] = 'ạ';
		arrayOfChar6[1] = 'ẹ';
		arrayOfChar6[2] = 'ị';
		arrayOfChar6[3] = 'ọ';
		arrayOfChar6[4] = 'ụ';
		arrayOfChar6[5] = 'ỵ';
		arrayOfChar6[6] = 'ậ';
		arrayOfChar6[7] = 'ệ';
		arrayOfChar6[8] = 'ộ';
		arrayOfChar6[9] = 'ặ';
		arrayOfChar6[10] = 'ợ';
		arrayOfChar6[11] = 'ự';
		arrayOfChar6[12] = 'Ạ';
		arrayOfChar6[13] = 'Ẹ';
		arrayOfChar6[14] = 'Ị';
		arrayOfChar6[15] = 'Ọ';
		arrayOfChar6[16] = 'Ụ';
		arrayOfChar6[17] = 'Ỵ';
		arrayOfChar6[18] = 'Ậ';
		arrayOfChar6[19] = 'Ệ';
		arrayOfChar6[20] = 'Ộ';
		arrayOfChar6[21] = 'Ặ';
		arrayOfChar6[22] = 'Ợ';
		arrayOfChar6[23] = 'Ự';
		
//		arrayOfChar[0] = arrayOfChar1;
//		arrayOfChar[1] = arrayOfChar2;
//		arrayOfChar[2] = arrayOfChar3;
//		arrayOfChar[3] = arrayOfChar4;
//		arrayOfChar[4] = arrayOfChar5;
//		arrayOfChar[5] = arrayOfChar6;
		
		try {
			InputStream is = getAssets().open("mapping_pattern.xml");
			PatternRecognizer.parseXmlFile(is);

			gestureDetector = new GestureDetector(this,
					new GestureDetector.SimpleOnGestureListener() {

						public void onLongPress(MotionEvent e) {
							Log.e("", "Longpress detected");
						}

						private static final int SWIPE_MIN_DISTANCE = 60;
						private static final int SWIPE_MAX_OFF_PATH = 250;
						private static final int SWIPE_THRESHOLD_VELOCITY = 50;

						public boolean onFling(MotionEvent e1, MotionEvent e2,
								float velocityX, float velocityY) {
							Log.d("---onFling---",
									e1.toString() + e2.toString() + "");

							try {
								// if (Math.abs(e1.getY() - e2.getY()) >
								// SWIPE_MAX_OFF_PATH)
								// return false;
								// up to down swipe
								if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE
										&& Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
									// do your code

									if (keyboardLayout.findViewById(
											R.id.leftrow).getVisibility() == View.GONE) {
										keyboardLayout.findViewById(
												R.id.leftrow).setVisibility(
												View.VISIBLE);
										keyboardLayout.findViewById(
												R.id.rightrow).setVisibility(
												View.GONE);
									} else {
										keyboardLayout.findViewById(
												R.id.leftrow).setVisibility(
												View.GONE);
										keyboardLayout.findViewById(
												R.id.rightrow).setVisibility(
												View.VISIBLE);
									}
									return true;

								} else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE
										&& Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
									// left to right flip
									Toast.makeText(MainActivity.this,
											"Swipe Down", 0).show();
									handleClose();
									return true;
								}
								if (Math.abs(e1.getX() - e2.getX()) > SWIPE_MAX_OFF_PATH)
									return false;
							} catch (Exception e) {
								// nothing
							}
							return false;
						}

					});

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Get the screen size in pixels
	 * 
	 * @param context
	 * @return
	 */
	public static Point getScreenSize(Context ctx) {
		WindowManager wm = (WindowManager) ctx
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();

		Point result = new Point();
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
			display.getSize(result);
		} else {
			result = new Point(display.getWidth(), display.getHeight());
		}

		return result;
	}

	/**
	 * Check the screen current orientation
	 * 
	 * @param context
	 * @return true if in landscape mode, false for portrait mode
	 */
	public static boolean getScreenOrientation(Context context) {
		Point screenSize = getScreenSize(context);
		if (screenSize.x > screenSize.y)
			return true;
		return false;
	}

	/**
	 * Get the screen physical size in inches
	 * 
	 * @param context
	 * @return width and height of screen physically
	 */
	public static double[] getPhysicalScreenSize(Context context) {
		DisplayMetrics metric = new DisplayMetrics();
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getMetrics(metric);

		double width = metric.widthPixels / metric.xdpi;
		double height = metric.heightPixels / metric.ydpi;

		return new double[] { width, height };
	}

	public static int getDPI(int size, DisplayMetrics metrics) {
		return (size * metrics.densityDpi) / DisplayMetrics.DENSITY_DEFAULT;
	}

	public static boolean isLargeScreen(Context context) {

		int screenSize = context.getResources().getConfiguration().screenLayout
				& Configuration.SCREENLAYOUT_SIZE_MASK;

		return screenSize >= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}

	/**
	 * Optimize the view for different screen size
	 */
	public void OptimizeViewForKeyboard() {
		fullKeyboard = true;
		Point pixelSize = getScreenSize(this);
		DisplayMetrics metric = getResources().getDisplayMetrics();
		double[] inch = getPhysicalScreenSize(this);

		boolean large = (Math.pow(inch[0], 2) + Math.pow(inch[1], 2)) > 25;

		// Point dpSize = new Point(getDPI(pixelSize.x,metric),
		// getDPI(pixelSize.y, metric));
		// 557*320
		if (!large || pixelSize.x < pixelSize.y) {
			fullKeyboard = false;
			keyboardLayout = getLayoutInflater().inflate(
					R.layout.oneside_keyboard, null);
		}
		;

		// adjust percentage

		TableLayout tbLayout = (TableLayout) keyboardLayout
				.findViewById(R.id.tableLayout);
		// Java. You succeed!
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, pixelSize.y * 7 / 10);

		tbLayout.setLayoutParams(lp);
		// keyboardLayout.findViewById(R.id.tableLayout).setLayoutParams(
		// new TableLayout.LayoutParams(pixelSize.x, pixelSize.y*7/10));
	}

	GestureDetector gestureDetector;

	@Override
	public View onCreateInputView() {
		// TODO Auto-generated method stub

		keyboardLayout = getLayoutInflater().inflate(R.layout.full_keyboard,
				null);

		// AjustSizePercentage();
		OptimizeViewForKeyboard();

		tblLayout = (TableLayout) keyboardLayout.findViewById(R.id.tableLayout);
		txtView = (TextView) tblLayout.findViewById(R.id.textView1);

		keyboardLayout.findViewById(R.id.btn_Space).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						keyDownUp(KeyEvent.KEYCODE_SPACE);
						char1 = 0; char2 = 0; char3 = 0;
					}
				});

		keyboardLayout.findViewById(R.id.btn_Enter).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						keyDownUp(KeyEvent.KEYCODE_ENTER);
						char1 = 0; char2 = 0; char3 = 0;
					}
				});

		keyboardLayout.findViewById(R.id.btn_Shift).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						handleShift();
						char1 = 0; char2 = 0; char3 = 0;
					}
				});

		keyboardLayout.findViewById(R.id.btn_Delete).setOnClickListener(
				new View.OnClickListener() {

					public void onClick(View v) {
						// TODO Auto-generated method stub
						handleBackspace();
					}
				});

		keyboardLayout.findViewById(R.id.btn_Done).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						handleClose();
						char1 = 0; char2 = 0; char3 = 0;
					}
				});

		keyboardLayout.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub

				int action = event.getAction() & MotionEvent.ACTION_MASK;
				pointerCount = event.getPointerCount();
				boolean gestureOK = gestureDetector.onTouchEvent(event);

				if (gestureOK)
					return true;
				TableLayout layout = (TableLayout) v
						.findViewById(R.id.tableLayout);
				TableRow tbleRow = (TableRow) layout.getChildAt(1);

				if (!fullKeyboard) {
					if (keyboardLayout.findViewById(R.id.leftrow)
							.getVisibility() == View.GONE)
						tbleRow = (TableRow) layout.getChildAt(2);
				}

				RectF outRect = new RectF(tbleRow.getLeft(), tbleRow.getTop(),
						tbleRow.getRight(), tbleRow.getBottom());
				switch (action) {
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_POINTER_DOWN:
					if (!touchLetters) {
						int pointerIndex = event.getActionIndex();
						float x = event.getX(pointerIndex), y = event
								.getY(pointerIndex);
						if (outRect.contains(x, y)) {
							touchLetters = true;
							touchLocation = new PointF(x, y);
							touchID = event.getPointerId(pointerIndex);
						}
					}
					break;
				case MotionEvent.ACTION_UP:
				case MotionEvent.ACTION_POINTER_UP:

					break;
				case MotionEvent.ACTION_MOVE:
					if (touchLetters) {
						int pointerIndex = event.findPointerIndex(touchID);
						if (pointerIndex != INVALID_POINTER_ID) {
							float x = event.getX(pointerIndex), y = event
									.getY(pointerIndex);
							touchLocation = new PointF(x, y);
						}
					}
					break;
				}

				if (touchLetters) {
					for (int i = 0; i < tbleRow.getChildCount(); i++) {
						View view = tbleRow.getChildAt(i);
						outRect = new RectF(view.getLeft(), view.getTop(), view
								.getRight(), tbleRow.getBottom());
						if (outRect.contains(touchLocation.x, touchLocation.y)) {

							int order = ((ImageButton) view).getId();
							switch (order) {
							case R.id.L1Btn:
								if (!bitSet.get(0)) {
									bitSet.set(0);
									lPattern += "1";
									((ImageButton) keyboardLayout
											.findViewById(R.id.L1Btn))
											.setImageResource(R.drawable.s_on);
									flag = true;
								}
								break;
							case R.id.L2Btn:
								if (!bitSet.get(1)) {
									bitSet.set(1);
									lPattern += "2";
									((ImageButton) keyboardLayout
											.findViewById(R.id.L2Btn))
											.setImageResource(R.drawable.n_on);
									flag = true;
								}
								break;
							case R.id.L3Btn:
								if (!bitSet.get(2)) {
									bitSet.set(2);
									lPattern += "3";
									((ImageButton) keyboardLayout
											.findViewById(R.id.L3Btn))
											.setImageResource(R.drawable.t_on);
									flag = true;
								}
								break;
							case R.id.L4Btn:
								if (!bitSet.get(3)) {
									bitSet.set(3);
									lPattern += "4";
									((ImageButton) keyboardLayout
											.findViewById(R.id.L4Btn))
											.setImageResource(R.drawable.h_on);
									flag = true;
								}
								break;
							case R.id.L5Btn:
								if (!bitSet.get(4)) {
									bitSet.set(4);
									lPattern += "5";
									((ImageButton) keyboardLayout
											.findViewById(R.id.L5Btn))
											.setImageResource(R.drawable.d_on);
									flag = true;
								}
								break;
							case R.id.R1Btn:
								if (!bitSet.get(5)) {
									bitSet.set(5);
									rPattern += "1";
									((ImageButton) keyboardLayout
											.findViewById(R.id.R1Btn))
											.setImageResource(R.drawable.a_on);
									flag = false;
								}
								break;
							case R.id.R2Btn:
								if (!bitSet.get(6)) {
									bitSet.set(6);
									rPattern += "2";
									((ImageButton) keyboardLayout
											.findViewById(R.id.R2Btn))
											.setImageResource(R.drawable.o_on);
									flag = false;
								}
								break;
							case R.id.R3Btn:
								if (!bitSet.get(7)) {
									bitSet.set(7);
									rPattern += "3";
									((ImageButton) keyboardLayout
											.findViewById(R.id.R3Btn))
											.setImageResource(R.drawable.e_on);
									flag = false;
								}
								break;
							case R.id.R4Btn:
								if (!bitSet.get(8)) {
									bitSet.set(8);
									rPattern += "4";
									((ImageButton) keyboardLayout
											.findViewById(R.id.R4Btn))
											.setImageResource(R.drawable.u_on);
									flag = false;
								}
								break;
							case R.id.R5Btn:
								if (!bitSet.get(9)) {
									bitSet.set(9);
									rPattern += "5";
									((ImageButton) keyboardLayout
											.findViewById(R.id.R5Btn))
											.setImageResource(R.drawable.i_on);
									flag = false;
								}
								break;
							default:
								break;
							}
						}
					}
				}
				String kt = PatternRecognizer.translateToCharacter(lPattern,
						shifted);
				String kt1 = PatternRecognizer.translateToCharacter(rPattern,
						shifted);

				if (flag) {
					txtView.setText(kt);
				} else {
					txtView.setText(kt1);
				}

				if ((action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP)
						&& event.findPointerIndex(event.getActionIndex()) == touchID) {
					if (touchLetters) {
						if (event.findPointerIndex(event.getActionIndex()) == touchID) {
							// TODO:
							touchLetters = false;
							touchID = INVALID_POINTER_ID;
							touchLocation = new PointF();
						}
					}
					if (flag) {
						if (kt != null) {
							kt = translateToVietnamese(kt);
							drawButton();
							commitText(kt);
						} else {
							drawButton();
						}
					} else {
						if (kt1 != null) {
							kt1 = translateToVietnamese(kt1);
							drawButton();
							commitText(kt1);
						} else {
							drawButton();
						}
					}
				}

				return true;
			}
		});
		return keyboardLayout;
	}
	
	private String translateToVietnamese(String input) {
		if (char1 == 0) {
			char1 = input.charAt(0);
			if (char2 != 0) {
				if (char1 == char2) {
					handleBackspace();
					commitText(String.valueOf(char1));
					char1 = 0;
					char2 = 0;
					char3 = 0;
				}
				else {
					switch (char1) {
					case 's':
						input = translateWithMark(char3, 2, String.valueOf(char3));
						break;
					case 'f':
						input = translateWithMark(char3, 3, String.valueOf(char3));
						break;
					case 'r':
						input = translateWithMark(char3, 4, String.valueOf(char3));
						break;
					case 'x':
						input = translateWithMark(char3, 5, String.valueOf(char3));
						break;
					case 'j':
						input = translateWithMark(char3, 6, String.valueOf(char3));
						break;
					default:
						char1 = 0; char2 = 0; char3 = 0;
						break;
					}
				}
			}
		} else {
			char2 = input.charAt(0);
			switch (char1) {
			case 'a':
				switch (char2) {
				case 'a':
					input = "â";
					char3 = 'â';
					handleBackspace();
					break;
				case 'w':
					input = "ă";
					char3 = 'ă';
					handleBackspace();
					break;
				case 's':
					input = translateWithMark(char1, 2, input);
					break;
				case 'f':
					input = translateWithMark(char1, 3, input);
					break;
				case 'r':
					input = translateWithMark(char1, 4, input);
					break;
				case 'x':
					input = translateWithMark(char1, 5, input);
					break;
				case 'j':
					input = translateWithMark(char1, 6, input);
					break;
				default:
					char1 = 0; char2 = 0; char3 = 0;
					break;
				}
				break;
			case 'e':
				switch (char2) {
				case 'e':
					input = "ê";
					char3 = 'ê';
					handleBackspace();
					break;
				case 's':
					input = translateWithMark(char1, 2, input);
					break;
				case 'f':
					input = translateWithMark(char1, 3, input);
					break;
				case 'r':
					input = translateWithMark(char1, 4, input);
					break;
				case 'x':
					input = translateWithMark(char1, 5, input);
					break;
				case 'j':
					input = translateWithMark(char1, 6, input);
					break;
				default:
					char1 = 0; char2 = 0; char3 = 0;
					break;
				}
				break;
			case 'o':
				switch (char2) {
				case 'o':
					input = "ô";
					char3 = 'ô';
					handleBackspace();
					break;
				case 'w':
					input = "ơ";
					char3 = 'ơ';
					handleBackspace();
					break;
				case 's':
					input = translateWithMark(char1, 2, input);
					break;
				case 'f':
					input = translateWithMark(char1, 3, input);
					break;
				case 'r':
					input = translateWithMark(char1, 4, input);
					break;
				case 'x':
					input = translateWithMark(char1, 5, input);
					break;
				case 'j':
					input = translateWithMark(char1, 6, input);
					break;
				default:
					char1 = 0; char2 = 0; char3 = 0;
					break;
				}
				break;
			case 'u':
				switch (char2) {
				case 'w':
					input = "ư";
					char3 = 'ư';
					handleBackspace();
					break;
				case 's':
					input = translateWithMark(char1, 2, input);
					break;
				case 'f':
					input = translateWithMark(char1, 3, input);
					break;
				case 'r':
					input = translateWithMark(char1, 4, input);
					break;
				case 'x':
					input = translateWithMark(char1, 5, input);
					break;
				case 'j':
					input = translateWithMark(char1, 6, input);
					break;
				default:
					char1 = 0; char2 = 0; char3 = 0;
					break;
				}
				break;
			case 'd':
				switch (char2) {
				case 'd':
					input = "đ";
					char1 = 0; char2 = 0; char3 = 0;
					handleBackspace();
					break;
				default:
					char1 = 0; char2 = 0; char3 = 0;
					break;
				}
				break;
			case 'A':
				switch (char2) {
				case 'a': case 'A':
					input = "Â";
					char3 = 'Â';
					handleBackspace();
					break;
				case 'w': case 'W':
					input = "Ă";
					char3 = 'Ă';
					handleBackspace();
					break;
				case 's': case 'S':
					input = translateWithMark(char1, 2, input);
					break;
				case 'f': case 'F':
					input = translateWithMark(char1, 3, input);
					break;
				case 'r': case 'R':
					input = translateWithMark(char1, 4, input);
					break;
				case 'x': case 'X':
					input = translateWithMark(char1, 5, input);
					break;
				case 'j': case 'J':
					input = translateWithMark(char1, 6, input);
					break;
				default:
					char1 = 0; char2 = 0; char3 = 0;
					break;
				}
				break;
			case 'E':
				switch (char2) {
				case 'e': case 'E':
					input = "Ê";
					char3 = 'Ê';
					handleBackspace();
					break;
				case 's': case 'S':
					input = translateWithMark(char1, 2, input);
					break;
				case 'f': case 'F':
					input = translateWithMark(char1, 3, input);
					break;
				case 'r': case 'R':
					input = translateWithMark(char1, 4, input);
					break;
				case 'x': case 'X':
					input = translateWithMark(char1, 5, input);
					break;
				case 'j': case 'J':
					input = translateWithMark(char1, 6, input);
					break;
				default:
					char1 = 0; char2 = 0; char3 = 0;
					break;
				}
				break;
			case 'O':
				switch (char2) {
				case 'o': case 'O':
					input = "Ô";
					char3 = 'Ô';
					handleBackspace();
					break;
				case 'w': case 'W':
					input = "Ơ";
					char3 = 'Ơ';
					handleBackspace();
					break;
				case 's': case 'S':
					input = translateWithMark(char1, 2, input);
					break;
				case 'f': case 'F':
					input = translateWithMark(char1, 3, input);
					break;
				case 'r': case 'R':
					input = translateWithMark(char1, 4, input);
					break;
				case 'x': case 'X':
					input = translateWithMark(char1, 5, input);
					break;
				case 'j': case 'J':
					input = translateWithMark(char1, 6, input);
					break;
				default:
					char1 = 0; char2 = 0; char3 = 0;
					break;
				}
				break;
			case 'U':
				switch (char2) {
				case 'w': case 'W':
					input = "Ư";
					char3 = 'Ư';
					handleBackspace();
					break;
				case 's': case 'S':
					input = translateWithMark(char1, 2, input);
					break;
				case 'f': case 'F':
					input = translateWithMark(char1, 3, input);
					break;
				case 'r': case 'R':
					input = translateWithMark(char1, 4, input);
					break;
				case 'x': case 'X':
					input = translateWithMark(char1, 5, input);
					break;
				case 'j': case 'J':
					input = translateWithMark(char1, 6, input);
					break;
				default:
					char1 = 0; char2 = 0; char3 = 0;
					break;
				}
				break;
			case 'D':
				switch (char2) {
				case 'd': case 'D':
					input = "Đ";
					handleBackspace();
					char1 = 0; char2 = 0; char3 = 0;
					break;
				default:
					char1 = 0; char2 = 0; char3 = 0;
					break;
				}
				break;
			default:
				char1 = 0; char2 = 0; char3 = 0;
				break;
			}
			char1 = 0;
		}
		return input;
	}

	private String translateWithMark(char cha, int index, String input) {
		for (int i = 0; i < 24; i++) {
			if (cha == arrayOfChar1[i]) {
				switch (index) {
				case 2:
					input = String.valueOf(arrayOfChar2[i]);
					break;
				case 3:
					input = String.valueOf(arrayOfChar3[i]);
					break;
				case 4:
					input = String.valueOf(arrayOfChar4[i]);
					break;
				case 5:
					input = String.valueOf(arrayOfChar5[i]);
					break;
				case 6:
					input = String.valueOf(arrayOfChar6[i]);
					break;
				default:
					break;
				}
			}
		}
		handleBackspace();
		char1 = 0;
		char2 = 0;
		char3 = 0;
		return input;
	}
	
	private void drawButton() {
		lPattern = "L";
		rPattern = "R";
		bitSet.clear(0, 10);
		((ImageButton) keyboardLayout.findViewById(R.id.L1Btn))
				.setImageResource(R.drawable.s_off);
		((ImageButton) keyboardLayout.findViewById(R.id.L2Btn))
				.setImageResource(R.drawable.n_off);
		((ImageButton) keyboardLayout.findViewById(R.id.L3Btn))
				.setImageResource(R.drawable.t_off);
		((ImageButton) keyboardLayout.findViewById(R.id.L4Btn))
				.setImageResource(R.drawable.h_off);
		((ImageButton) keyboardLayout.findViewById(R.id.L5Btn))
				.setImageResource(R.drawable.d_off);

		((ImageButton) keyboardLayout.findViewById(R.id.R1Btn))
				.setImageResource(R.drawable.a_off);
		((ImageButton) keyboardLayout.findViewById(R.id.R2Btn))
				.setImageResource(R.drawable.o_off);
		((ImageButton) keyboardLayout.findViewById(R.id.R3Btn))
				.setImageResource(R.drawable.e_off);
		((ImageButton) keyboardLayout.findViewById(R.id.R4Btn))
				.setImageResource(R.drawable.u_off);
		((ImageButton) keyboardLayout.findViewById(R.id.R5Btn))
				.setImageResource(R.drawable.i_off);
	}

	private void handleClose() {
		// commitTyped(getCurrentInputConnection());
		keyDownUp(KeyEvent.KEYCODE_ENTER);
		requestHideSelf(0);
	}

	private void handleShift() {
		// if (keepshifted) shifted = true;
		shifted = !shifted;
		if (shifted)
			((ImageButton) keyboardLayout.findViewById(R.id.btn_Shift))
					.setImageResource(R.drawable.shift_on);
		else
			((ImageButton) keyboardLayout.findViewById(R.id.btn_Shift))
					.setImageResource(R.drawable.shift_off);
	}

	private void handleBackspace() {
		keyDownUp(KeyEvent.KEYCODE_DEL);
		if (char1 == 0 || char2 == 0 || char3 == 0) {
			char1 = 0;
			char2 = 0;
			char3 = 0;
		}
	}

	/**
	 * Helper to send a key down / key up pair to the current editor.
	 */
	private void keyDownUp(int keyEventCode) {
		getCurrentInputConnection().sendKeyEvent(
				new KeyEvent(KeyEvent.ACTION_DOWN, keyEventCode));
		getCurrentInputConnection().sendKeyEvent(
				new KeyEvent(KeyEvent.ACTION_UP, keyEventCode));
	}

	public void commitText(CharSequence text) {
		InputConnection ic = getCurrentInputConnection();
		if (ic == null)
			return;
		ic.beginBatchEdit();
		// if (mComposing.length() > 0) {
		// commitTyped(ic);
		// }

		ic.commitText(text, 1);		
		
		ic.endBatchEdit();
		// updateShiftKeyState(getCurrentInputEditorInfo());
	}

	@Override
	public void onKey(int primaryCode, int[] keyCodes) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPress(int primaryCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRelease(int primaryCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onText(CharSequence text) {
		// TODO Auto-generated method stub

	}

	@Override
	public void swipeDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void swipeLeft() {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Swiped left", 0).show();
	}

	@Override
	public void swipeRight() {
		// TODO Auto-generated method stub

	}

	@Override
	public void swipeUp() {
		// TODO Auto-generated method stub

	}

}