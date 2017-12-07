package zl.git;

import java.util.List;

/**
 * 判断一个点是否在多边形内的算法类，判断的 核心规则是以这个点做一个向右的水平射线；<br />
 * 当该射线与多边形的边相交的数量为奇数时，则相交，否则不相交。 <br />
 * 算法的详细说明和证明这里不细说，这里主要考虑很多边界情况；<br />
 * 第一：多边形顶点存在重复的点，比如正常的点序列为p1，p2，其中p1应该和p2不想等，但是却相等了。<br />
 * 第二：这个点本身就是多边形的顶点，这个时候也在多边形上，结果返回true； <br />
 * 第三：这个点不是顶点，但是这个点在多边形的边上，结果返回true；<br />
 * 
 * @author zl
 * 
 */
public class Algorithm {

	private Algorithm() {
	}

	/**
	 * 判断一个点是否在多边形内的算法，判断的核心规则是以这个点做一个向右的水平射线；<br />
	 * 当该射线与多边形的边相交的数量为奇数时，则相交，否则不相交。 <br />
	 * 算法的详细说明和证明这里不细说，这里主要考虑边界情况；<br />
	 * 第一：多边形顶点存在连续重复的点，比如点序列为p1，p2相等。<br />
	 * 第二：这个点本身就是多边形的某一个顶点，<br />
	 * 第三：这个点不是顶点，通过射线法则计算相交的数量（包含在边上的情况）；<br />
	 * 
	 * @param point
	 *            点
	 * @param polygon
	 *            多边形顶点坐标
	 * @return 点在多边形内包括在边上返回true，否则返回false
	 */
	public static boolean isPointInPolygon(Point point, List<Point> polygon) {
		boolean isIn = false;// 是否在多边形内，每一次都取否操作，奇数次就为true，偶数次还是false
		Point p0;
		Point p1;
		/**
		 * 开始遍历，取最后一个点到第一个点的那条线段作为遍历的初始线段
		 */
		for (int i = 0, j = polygon.size() - 1; i < polygon.size(); j = i++) {
			p0 = polygon.get(j);
			p1 = polygon.get(i);
			if (point.equals(p0))// 判断point是否就是顶点本身，这里不增加判断与p1是否相等，可避免重复判断，下一次就会判断是否在p1上,缺点是会多判断一次边
				return true;
			if (isIntersectWithLine(point, p0, p1))// 如果相交，则记录相交次数（通过奇数、偶数来设置isIn的值）
				isIn = !isIn;
		}
		return isIn;
	}

	/**
	 * 判断点p作向右水平射线是否与边p0、p1相交。<br />
	 * 点p水平射线与多边形边p1、p2是否相交的判断逻辑：<br />
	 * 前提逻辑：如果点p1、p2的y值都大于或者小于p的y值，直接返回false；<br />
	 * 第一：通过计算出p0、p1的方程，计算出当y值于p的y值相等时候的x值，如果x>p.x，则相交<br />
	 * 第二：如果p点本身就在p0、p1的线段上，那么计算出来的x=p.x<br />
	 * 总结结果：通过计算当y=p.y时，线段p0、p1的方程式解出来的x值，如果x>=p.x则相交
	 * 
	 * @param p
	 *            需要判断的点
	 * @param p0
	 *            多边形边的起始点
	 * @param p1
	 *            多边形边的终结点
	 * @return 如果相交true，否则false
	 */
	private static boolean isIntersectWithLine(Point p, Point p0, Point p1) {
		boolean p0Flag = (p0.getY() - p.getY()) > 0;// p0点是否在直线y=p.getY()的上方；
		boolean p1Flag = (p1.getY() - p.getY()) > 0;// p1点是否在直线y=p.getY()的上方；
		if (p0Flag == p1Flag)// 直线同侧，那么必定不相交
			return false;
		/**
		 * 确定线段在射线的两侧，求解当射线方程中y值取p.getY()时的x值 <br />
		 * 根据x=kx+b求解可得x=( (y-y1)*x0-(y-y0)*x1 ) / (y0-y1),如果x>=p.getX()，相交<br />
		 * 这个方程式可变成：x=( (x0 -x1)*(y-y1) ) / (y0-y1) + x1(网上多使用这个方程，少做一次乘法)
		 */
		double x = (p0.getX() - p1.getX()) * (p.getY() - p1.getY())
				/ (p0.getY() - p1.getY()) + p1.getX();
		/**
		 * 当x = p.getX()时，这个点在线段上，也属于相交 <br />
		 * java本身好像对浮点型就有较为精确的计算结果，否则需要定义一个误差变量
		 */
		return x >= p.getX();
	}
}
