package com.riil.optimage;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;


/**
 * 根据图片搜索大图中的位置
 *
 */
public class SearchPixelPosition {
	//需要找的图片宽度
	private int targetWidth;
	//需要找的图片高度
	private int targetHeight;

	BufferedImage bi = null;

	/**
	 * 截屏
	 * @return
	 * @throws Exception 
	 */
	public BufferedImage getFullScreenShot() throws Exception {
		BufferedImage bfImage = null;
		int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		System.out.println("此电脑分辨率：" + width+" "+height);
		File f = new File("abc.png");
		try {
			Robot robot = new Robot();
			bfImage = robot.createScreenCapture(new Rectangle(0, 0, width, height));
			ImageIO.write(bfImage, "png", f);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		return bfImage;
	}
	/**
	 * 对大图进行所有像素点寻找，直到满足5个点，返回之后到的坐标值
	 * @param path
	 * @param target
	 * @return
	 * @throws Exception 
	 */
	public ResultBean getAllRGB(String target) throws Exception {
		bi =  getFullScreenShot();


		int width = bi.getWidth();
		int height = bi.getHeight();
		int minx = bi.getMinX();
		int miny = bi.getMinY();
		ArrayList<PositionBean> setTarget5RGB = setTarget5RGB(target);

		for (int i = minx; i < width; i++) {
			for (int j = miny; j < height; j++) {
				int pixel = bi.getRGB(i, j);
				//依次对比5个点。
				if (setTarget5RGB != null) {
					PositionBean p1 = setTarget5RGB.get(0);
					if (pixel == p1.pxrgb) {
						int other = 0;
						PositionBean p2 = setTarget5RGB.get(1);
						int pixel2 = bi.getRGB(i + (p2.x - p1.x), j);
						if (pixel2 == p2.pxrgb) {
							other++;
							PositionBean p3 = setTarget5RGB.get(2);
							//System.out.print(i+" "+p3.x+" "+p1.x+" "+p3.y+" "+p1.y+"\n");
							int pixel3 = bi.getRGB(i + (p3.x - p1.x), j + (p3.y - p1.y));
							if (pixel3 == p3.pxrgb) {
								other++;
								PositionBean p4 = setTarget5RGB.get(3);
								int pixel4 = bi.getRGB(i, j + (p4.y - p1.y));
								if (pixel4 == p4.pxrgb) {
									other++;
									PositionBean p5 = setTarget5RGB.get(4);
									int pixel5 = bi.getRGB(i + (p5.x - p1.x), j + (p5.y - p1.y));
									if (pixel5 == p5.pxrgb) {
										other++;
									}
								}
							}
						}
						if (other == 4) {
							System.out.println("找到了 !   ===》》》》横坐标: " + i + "   纵坐标: " + j);
							ResultBean resultBean = new ResultBean();
							resultBean.width = targetWidth;
							resultBean.height = targetHeight;
							resultBean.x = i - p1.x;
							resultBean.y = j - p1.y;
							//System.out.println(resultBean.width+" "+resultBean.height+" "+ resultBean.x+" "+resultBean.y);
							return resultBean;
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * 分别取小图的四个角落和中心点的像素，作为搜图依据
	 * 
	 * @param src
	 * @return
	 * @throws Exception
	 */
	private ArrayList<PositionBean> get5PointForTarget(String src) throws Exception {
		ArrayList<PositionBean> searchXYList = new ArrayList<>();
		File file = new File(src);
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int width = bi.getWidth();
		int height = bi.getHeight();
		targetWidth = width;
		targetHeight = height;

		if (width >= 10 && height >= 10) {
			int px1 = (int) (width * 0.25);
			int py1 = (int) (height * 0.25);
			int px2 = (int) (width * 0.75);
			int py2 = (int) (height * 0.25);
			int px3 = (int) (width * 0.5);
			int py3 = (int) (height * 0.5);
			int px4 = (int) (width * 0.25);
			int py4 = (int) (height * 0.75);
			int px5 = (int) (width * 0.75);
			int py5 = (int) (height * 0.75);
			searchXYList.add(new PositionBean(px1, py1));
			searchXYList.add(new PositionBean(px2, py2));
			searchXYList.add(new PositionBean(px3, py3));
			searchXYList.add(new PositionBean(px4, py4));
			searchXYList.add(new PositionBean(px5, py5));
		} else {
			throw new Exception("不支持10px以内的搜索");
		}

		return searchXYList;
	}

	/**
	 * 设置5个点的像素值 和对应的坐标
	 * @param src
	 * @return
	 */
	private ArrayList<PositionBean> setTarget5RGB(String src) {
		File file = new File(src);
		BufferedImage ci = null;
		try {
			ci = ImageIO.read(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ArrayList<PositionBean> get5PointForTarget = get5PointForTarget(src);
			for (int i = 0; i < get5PointForTarget.size(); i++) {
				PositionBean positionBean = get5PointForTarget.get(i);
				positionBean.pxrgb = ci.getRGB(positionBean.x, positionBean.y);
			}
			return get5PointForTarget;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
