//package main.java.model.product;
package model.product;

//import main.java.model.member.BuyerDO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import model.member.BuyerDO;
//import model.member.SellerDO;

public class BeansDAO {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String sql;

	public BeansDAO() {
		String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
		String jdbc_url = "jdbc:oracle:thin:@localhost:1521:XE";

		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, "scott", "tiger");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 상품 '정보' 조회
	public BeansDO getBean(int beansNum) {

		BeansDO beans = new BeansDO();

		sql = "select bean_name, bean_price, bean_img, descript, delivery_charge, bean_thumbnail " + 
			  "from beans where beans_num = ?";

		try {
			this.pstmt = conn.prepareStatement(this.sql);
			this.pstmt.setInt(1, beansNum);
			rs = this.pstmt.executeQuery();

			if (rs.next()) {
				beans.setBeanName(rs.getString("bean_name"));
				beans.setBeanPrice(rs.getInt("bean_price"));
				beans.setBeanImg(rs.getString("bean_img"));
				beans.setDescript(rs.getString("descript"));
				beans.setDeliveryCharge(rs.getInt("delivery_charge"));
				beans.setBeanThumbnail(rs.getString("bean_thumbnail"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!this.pstmt.isClosed()) {
					this.pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return beans;
	}

	// 공동구매 상품 조회 (추가 수정 필요)
	public BeansDO getGroupBean(int beansNum) {
		BeansDO beans = new BeansDO();

		this.sql = "select * from beans where deadline is not null and beans_num = ?";

		try {
			this.pstmt = conn.prepareStatement(this.sql);
			this.pstmt.setInt(1, beansNum);
			rs = this.pstmt.executeQuery();

			if (rs.next()) {
				beans.setBeanName(rs.getString("bean_name"));
				beans.setBeanPrice(rs.getInt("bean_price"));
				beans.setBeanImg(rs.getString("bean_img"));
				beans.setDescript(rs.getString("descript"));
				beans.setDeliveryCharge(rs.getInt("delivery_charge"));
				beans.setBeanThumbnail(rs.getString("bean_thumbnail"));
				beans.setDeadline(rs.getString("deadline"));
				beans.setGoalQty(rs.getInt("goal_qty"));
				beans.setGoalPrice(rs.getInt("goal_price"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!this.pstmt.isClosed()) {
					this.pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return beans;
	}

	// 베스트 원두 5개
	public ArrayList<BeansDO> bestBeanArray() {

		ArrayList<BeansDO> beanList = new ArrayList<BeansDO>();
		sql = "select bean_img, like_count, rownum from" +
				"(select bean_img, like_count, rownum from beans order by like_count desc)" +
				"where rownum between 1 and 5"; 
    	try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			BeansDO beansDO = null;
			
			while(rs.next()) {
				beansDO = new BeansDO();
				
				beansDO.setBeanImg(rs.getString("bean_img"));
				beansDO.setLikeCount(rs.getInt("like_count"));
				
				beanList.add(beansDO);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {			
			try {
				if(!stmt.isClosed()) {
					stmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return beanList;
	}

	// 일반 상품 목록 정보 가져오기
	public ArrayList<BeansDO> getBeansList(String str) {


		ArrayList<BeansDO> resultList = new ArrayList<BeansDO>();

		sql = "select * from beans where deadline is null";

		try {
			this.pstmt = conn.prepareStatement(this.sql);
			rs = this.pstmt.executeQuery();

			while (rs.next()) {
				BeansDO beans = new BeansDO();

				beans.setBeansNum(rs.getInt("beans_num"));
				beans.setBeanName(rs.getString("bean_name"));
				beans.setBeanPrice(rs.getInt("bean_price"));
				beans.setBeanImg(rs.getString("bean_img"));
				beans.setLikeCount(rs.getInt("like_count"));
				beans.setDeadline(rs.getString("deadline"));
				beans.setGoalQty(rs.getInt("goal_qty"));
				beans.setGoalPrice(rs.getInt("goal_price"));
				
				if (str == null || beans.getBeanName().contains(str)) {
	                resultList.add(beans);
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (this.pstmt != null && !this.pstmt.isClosed()) {
					this.pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}

	// 공동구매 상품만 가져오기
	public ArrayList<BeansDO> getGroupBeansList(String str) {

		ArrayList<BeansDO> resultList = new ArrayList<BeansDO>();

		sql = "select * from beans where deadline is not null";

		try {
			this.pstmt = conn.prepareStatement(this.sql);
			rs = this.pstmt.executeQuery();

			while (rs.next()) {
				BeansDO beans = new BeansDO();

				beans.setBeansNum(rs.getInt("beans_num"));
				beans.setBeanName(rs.getString("bean_name"));
				beans.setBeanPrice(rs.getInt("bean_price"));
				beans.setBeanImg(rs.getString("bean_img"));
				beans.setLikeCount(rs.getInt("like_count"));
				beans.setDeadline(rs.getString("deadline"));
				beans.setGoalQty(rs.getInt("goal_qty"));
				beans.setGoalPrice(rs.getInt("goal_price"));

				if (str == null || beans.getBeanName().contains(str)) {
	                resultList.add(beans);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (this.pstmt != null && !this.pstmt.isClosed()) {
					this.pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}

	// 일반 상품 페이징 정렬
	public ArrayList<BeansDO> sortedPage(String sort, String str) {
	    ArrayList<BeansDO> allBeanList = getBeansList(str);

	    // 정렬
	    if (sort != null && !sort.equals("default")) {
	        if (sort.equals("bestSelling")) {
	            // 판매량 순 정렬(판매량 수가 같을시 좋아요 순, 좋아요 순도 같을 시 최신 상품 순)
	            Collections.sort(allBeanList, new Comparator<BeansDO>() {
	                @Override
	                public int compare(BeansDO sc1, BeansDO sc2) {
	                    Integer sellCount1 = sc1.getBeanTotalSellCount();
	                    Integer sellCount2 = sc2.getBeanTotalSellCount();
	                    if (sellCount1 != sellCount2) {
	                        return sellCount2.compareTo(sellCount1);
	                    } else {
	                        Integer beansNum1 = sc1.getBeansNum();
	                        Integer beansNum2 = sc2.getBeansNum();
	                        return beansNum2.compareTo(beansNum1);
	                    }
	                }
	            });
	        } else if (sort.equals("mostLiked")) {
	            // 좋아요 순 정렬 (좋아요 수가 같을 시 최신 상품 순)
	            Collections.sort(allBeanList, new Comparator<BeansDO>() {
	                public int compare(BeansDO lc1, BeansDO lc2) {
	                    Integer likeCount1 = lc1.getLikeCount();
	                    Integer likeCount2 = lc2.getLikeCount();
	                    if (likeCount1 != likeCount2) {
	                        return likeCount2.compareTo(likeCount1);
	                    } else {
	                        Integer beansNum1 = lc1.getBeansNum();
	                        Integer beansNum2 = lc2.getBeansNum();
	                        return beansNum2.compareTo(beansNum1);
	                    }
	                }
	            });
	        }
	    } else {
	        // 기본 정렬: 최신 등록 순
	        Collections.sort(allBeanList, new Comparator<BeansDO>() {
	            @Override
	            public int compare(BeansDO bn1, BeansDO bn2) {
	                Integer beansNum1 = bn1.getBeansNum();
	                Integer beansNum2 = bn2.getBeansNum();
	                return beansNum2.compareTo(beansNum1);
	            }
	        });
	    }
	    return allBeanList;
	}

	// 공동 구매 상품 페이징 정렬
	public ArrayList<BeansDO> sortedPage2(String sort, String str) {
		ArrayList<BeansDO> allBeanList = getGroupBeansList(str);

		// 정렬
		if (sort != null && !sort.equals("default")) {
			if (sort.equals("bestSelling")) {
				// 판매량 순 정렬
				Collections.sort(allBeanList, new Comparator<BeansDO>() {
					@Override
					public int compare(BeansDO sc1, BeansDO sc2) {
						Integer sellCount1 = sc1.getBeanTotalSellCount();
						Integer sellCount2 = sc2.getBeanTotalSellCount();
						if (sellCount1 != sellCount2) {
							return sellCount2.compareTo(sellCount1);
						} else if (sellCount1 == sellCount2) {
							Integer likeCount1 = sc1.getLikeCount();
							Integer likeCount2 = sc2.getLikeCount();
							return likeCount2.compareTo(likeCount1);
						} else {
							Integer beansNum1 = sc1.getBeansNum();
							Integer beansNum2 = sc2.getBeansNum();
							return beansNum2.compareTo(beansNum1);
						}
					}
				});
			} else if (sort.equals("mostLiked")) {
				// 좋아요 순 정렬 (수가 같을시 판매량 순
				Collections.sort(allBeanList, new Comparator<BeansDO>() {
					public int compare(BeansDO lc1, BeansDO lc2) {
						Integer likeCount1 = lc1.getLikeCount();
						Integer likeCount2 = lc2.getLikeCount();
						if (likeCount1 != likeCount2) {
							return likeCount2.compareTo(likeCount1);
						} else if (likeCount1 == likeCount2) {
							Integer sellCount1 = lc1.getBeanTotalSellCount();
							Integer sellCount2 = lc2.getBeanTotalSellCount();
							return sellCount2.compareTo(sellCount1);
						} else {
							Integer beansNum1 = lc1.getBeansNum();
							Integer beansNum2 = lc2.getBeansNum();
							return beansNum2.compareTo(beansNum1);
						}
					}
				});
			}
		} else {
			// 기본 정렬: 최신 등록 순
			Collections.sort(allBeanList, new Comparator<BeansDO>() {
				@Override
				public int compare(BeansDO bn1, BeansDO bn2) {
					Integer beansNum1 = bn1.getBeansNum();
					Integer beansNum2 = bn2.getBeansNum();
					return beansNum2.compareTo(beansNum1);
				}
			});
		}
		return allBeanList;
	}

	// 총 행 수를 구하는 메서드
	public int getTotalRows() {
		int totalRows = 0;
		sql = "select count(*) from beans";

		try {
			this.pstmt = conn.prepareStatement(this.sql);
			rs = this.pstmt.executeQuery();

			if (rs.next()) {
				totalRows = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!this.pstmt.isClosed()) {
					this.pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return totalRows;
	}

	// 일반 판매 상품 등록하기
	// beans_num : 자동
	// 판매자 이메일, 카테고리 번호, 원두 이름, 원두 가격, 원두 이미지, 원두 상세설명(이미지), 배송비, 원두 썸네일
	public int insertBean(BeansDO beansDO) {
		int rowCount = 0;
		try {
			this.conn.setAutoCommit(false);

			this.sql = "INSERT INTO BEANS (BEANS_NUM, SELLER_EMAIL, CATEGORY_NUM, BEAN_NAME, BEAN_PRICE, "
					+ "BEAN_IMG, DESCRIPT, DELIVERY_CHARGE)"
					+ "VALUES (seq_beans_beans_num.nextval, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, beansDO.getSellerEmail());
			pstmt.setInt(2, beansDO.getCategoryNum());
			pstmt.setString(3, beansDO.getBeanName());
			pstmt.setInt(4, beansDO.getBeanPrice());
			pstmt.setString(5, beansDO.getBeanImg());
			pstmt.setString(6, beansDO.getDescript());
			pstmt.setInt(7, beansDO.getDeliveryCharge());

			rowCount = pstmt.executeUpdate();
			this.conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.conn.setAutoCommit(true);

				if (!pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return rowCount;
	}

	// 공동 구매 상품 등록하기

	public int insertGroupBean(BeansDO beansDO) {
		int rowCount = 0;
		try {
			this.conn.setAutoCommit(false);

			if (!rs.next()) {
				this.sql = "insert into beans (beans_num, seller_email, bean_name, bean_price, goal_price, category_num, goal_qty, deadline, delivery_charge, descript, bean_img)"
						+ "values (seq_beans_beans_num.nextval, ?, ?,?, ?, ?, ?, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, beansDO.getSellerEmail());
				pstmt.setString(2, beansDO.getBeanName());
				pstmt.setInt(3, beansDO.getBeanPrice());
				pstmt.setInt(4, beansDO.getGoalPrice());
				pstmt.setInt(5, beansDO.getCategoryNum());
				pstmt.setInt(6, beansDO.getGoalQty());
				pstmt.setString(7, beansDO.getDeadline());
				pstmt.setInt(8, beansDO.getDeliveryCharge());
				pstmt.setString(10, beansDO.getDescript());
				pstmt.setString(11, beansDO.getBeanImg());

				rowCount = pstmt.executeUpdate();
				this.conn.commit();
			} else {
				this.conn.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.conn.setAutoCommit(true);

				if (!pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return rowCount;
	}

	// 등록한 물품을 판매종료 로 만들기 -> status를 바꾸기, 1이면 판매종료
	public int beansSoldout(BeansDO beansDO) {
		int rowCount = 0;

		this.sql = "update beans set status = 1 where beans_num = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beansDO.getBeansNum());
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return rowCount;
	}

	// 상품 정보 수정하기
	public int modifyBeans(BeansDO beansDO) {
		int rowCount = 0;
		this.sql = "update beans set bean_name = ?, bean_price = ?, "
				+ "delivery_charge =?, bean_img = ?, descript = ? where beans_num = ?";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, beansDO.getBeanName());
			pstmt.setInt(2, beansDO.getBeanPrice());
			pstmt.setInt(3, beansDO.getDeliveryCharge());
			pstmt.setString(4, beansDO.getBeanImg());
			pstmt.setString(5, beansDO.getDescript());
			pstmt.setInt(7, beansDO.getBeansNum());
			rowCount = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return rowCount;
	}

	// 카테고리 번호목록(국기 리스트)가져오기 - 카테고리 컬럼에 이미지로 대체할듯
	public ArrayList<CategoryDO> getAllCategory() {
		ArrayList<CategoryDO> categoryList = new ArrayList<CategoryDO>();
		this.sql = "select c_name from category";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			CategoryDO category = null;

			while (rs.next()) {
				category = new CategoryDO();

				category.setCategoryName(rs.getString("c_name"));

				categoryList.add(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!stmt.isClosed()) {
					stmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return categoryList;
	}

	// 원두 정렬하기 - 원산지별

	public ArrayList<BeansDO> arrayOrigin(String cName) {
		ArrayList<BeansDO> beanList = new ArrayList<BeansDO>();

		this.sql = "select beans.bean_name, beans.bean_img, beans.like_count  "
				+ "from beans join category on category.category_num = beans.category_num where c_name = ?"
				+ "order by beans.beans_regdate desc";
		try {
			this.pstmt = conn.prepareStatement(sql);

			this.pstmt.setString(1, cName);
			rs = this.pstmt.executeQuery();

			while (rs.next()) {
				BeansDO bean = new BeansDO();
				bean.setBeanName(rs.getString("bean_name"));
				bean.setBeanImg(rs.getString("bean_img"));
				bean.setLikeCount(rs.getInt("like_count"));

				beanList.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return beanList;
	}

	// 좋아요 많은 순 정렬
	public ArrayList<BeansDO> arrayLikeCount() {
		ArrayList<BeansDO> beanList = new ArrayList<BeansDO>();
		this.sql = "select bean_name, bean_img, like_count from beans order by like_count desc";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			BeansDO beansDO = null;

			while (rs.next()) {
				beansDO = new BeansDO();

				beansDO.setBeanName(rs.getString("bean_name"));
				beansDO.setBeanImg(rs.getString("bean_img"));
				beansDO.setLikeCount(rs.getInt("like_count"));

				beanList.add(beansDO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!stmt.isClosed()) {
					stmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return beanList;
	}

	public ArrayList<BeansDO> arrayRecent() {
		ArrayList<BeansDO> beanList = new ArrayList<BeansDO>();
		this.sql = "select bean_name, bean_img, like_count from beans order by beans_regdate desc";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			BeansDO beansDO = null;

			while (rs.next()) {
				beansDO = new BeansDO();

				beansDO.setBeanName(rs.getString("bean_name"));
				beansDO.setBeanImg(rs.getString("bean_img"));
				beansDO.setLikeCount(rs.getInt("like_count"));

				beanList.add(beansDO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!stmt.isClosed()) {
					stmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
    	return beanList;
    }

	//likeCount 수정하기
	public int beansLikeCountUpdate(int beansNum, boolean bl){
		int rowCount = 0;
		if(bl){
			this.sql = "update beans set like_count = like_count + 1 where beans_num = ?";
		}
		else{
			this.sql = "update beans set like_count = like_count - 1 where beans_num = ?";
		}

		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beansNum);

			rowCount = pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(!pstmt.isClosed()) {
					pstmt.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}

		return rowCount;
	}

	//판매량 수정하기
	public int updateBeanTotalSellCount(int beansNum, int qty){
		int rowCount = 0;
		this.sql = "update beans set bean_total_selcount = bean_total_selcount + ? " +
				"where beans_num = ?";
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qty);
			pstmt.setInt(2, beansNum);

			rowCount = pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(!pstmt.isClosed()) {
					pstmt.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}

		return rowCount;
	}
}
