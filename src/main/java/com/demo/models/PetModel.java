package com.demo.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.demo.entities.Catalogs;
import com.demo.entities.Categorys;
import com.demo.entities.Pets;
import com.demo.entities.Users;

import DB.ConnectDB;

public class PetModel {
	// ham lay ra danh sach cac pet
	public List<Pets> findAll() {
		List<Pets> pets = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("select * from pets");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Pets pet = new Pets();
				pet.setId(resultSet.getInt("id"));
				pet.setPetName(resultSet.getString("petName"));
				pet.setPetType(resultSet.getString("petType"));
				pet.setPetGender(resultSet.getString("petGender"));
				pet.setDescription(resultSet.getString("description"));
				pet.setDetail(resultSet.getString("detail"));
				pet.setMade(resultSet.getString("made"));
				pet.setAmount(resultSet.getInt("amount"));
				pet.setMoney(resultSet.getDouble("money"));
				pet.setPetBirthday(resultSet.getDate("petBirthday"));
				pet.setImage(resultSet.getString("image"));
				pet.setStatus(resultSet.getBoolean("status"));
				pet.setCategoryId(resultSet.getInt("categoryId"));
				pet.setCatalogId(resultSet.getInt("catalogId"));
				pets.add(pet);

			}
		} catch (Exception e) {
			e.printStackTrace();
			pets = null;
		} finally {
			ConnectDB.disconnect();
		}
		return pets;
	}
	// Trả về danh sách pet từng trang
	public List<Pets> findPaginatedByCategory(int categoryId, int page, int pageSize) {
	    List<Pets> petsList = new ArrayList<>();
	    String sql = "SELECT * FROM pets WHERE categoryId = ? LIMIT ?, ?";
	    try (Connection con = ConnectDB.connection();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setInt(1, categoryId);
	        ps.setInt(2, (page - 1) * pageSize); // Vị trí bắt đầu cho phân trang
	        ps.setInt(3, pageSize); // Số bản ghi cần lấy
	        try (ResultSet resultSet = ps.executeQuery()) {
	            while (resultSet.next()) {
	                Pets pet = new Pets();
	                pet.setId(resultSet.getInt("id"));
	                pet.setPetName(resultSet.getString("petName"));
	                pet.setPetType(resultSet.getString("petType"));
	                pet.setPetGender(resultSet.getString("petGender"));
	                pet.setDescription(resultSet.getString("description"));
	                pet.setDetail(resultSet.getString("detail"));
	                pet.setMade(resultSet.getString("made"));
	                pet.setAmount(resultSet.getInt("amount"));
	                pet.setMoney(resultSet.getDouble("money"));
	                pet.setPetBirthday(resultSet.getDate("petBirthday"));
	                pet.setImage(resultSet.getString("image"));
	                pet.setStatus(resultSet.getBoolean("status"));
	                pet.setCategoryId(resultSet.getInt("categoryId"));
	                pet.setCatalogId(resultSet.getInt("catalogId"));
	                petsList.add(pet);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return petsList;
	}

	// Trả về số lượng pet từng trang
	public int getTotalPagesByCategory(int categoryId, int pageSize) {
	    int totalRecords = 0;
	    String sql = "SELECT COUNT(*) FROM pets WHERE categoryId = ?";
	    try (Connection con = ConnectDB.connection();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setInt(1, categoryId);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                totalRecords = rs.getInt(1);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return (int) Math.ceil(totalRecords * 1.0 / pageSize); // Tính toán tổng số trang
	}
	// ham tra ve danh sach pet tung category cho tung trang
	public List<Pets> findAllByCategoryWithPagination(int categoryId, int offset, int limit) {
        List<Pets> pets = new ArrayList<>();
        String sql = "SELECT * FROM pets WHERE categoryId = ? LIMIT ?, ?";

        try (Connection connection = ConnectDB.connection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, categoryId);
            statement.setInt(2, offset);
            statement.setInt(3, limit);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Pets pet = new Pets();
                pet.setId(resultSet.getInt("id"));
                pet.setPetName(resultSet.getString("petName"));
                pet.setPetType(resultSet.getString("petType"));
                pet.setPetGender(resultSet.getString("petGender"));
                pet.setDescription(resultSet.getString("description"));
                pet.setDetail(resultSet.getString("detail"));
                pet.setMade(resultSet.getString("made"));
                pet.setAmount(resultSet.getInt("amount"));
                pet.setMoney(resultSet.getDouble("money"));
                pet.setPetBirthday(resultSet.getDate("petBirthday"));
                pet.setImage(resultSet.getString("image"));
                pet.setStatus(resultSet.getBoolean("status"));
                pet.setCategoryId(resultSet.getInt("categoryId"));
                pet.setCatalogId(resultSet.getInt("catalogId"));

                pets.add(pet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pets;
    }

    public int getTotalCountByCategory(int categoryId) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM pets WHERE categoryId = ?";

        try (Connection connection = ConnectDB.connection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, categoryId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

	// tra ve so luong pet cu the gioi han
	public List<Pets> findAllByLimit(int limit) {
		List<Pets> pets = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("select * from pets LIMIT ? ");
			preparedStatement.setInt(1, limit);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Pets pet = new Pets();
				pet.setId(resultSet.getInt("id"));
				pet.setPetName(resultSet.getString("petName"));
				pet.setPetType(resultSet.getString("petType"));
				pet.setPetGender(resultSet.getString("petGender"));
				pet.setDescription(resultSet.getString("description"));
				pet.setDetail(resultSet.getString("detail"));
				pet.setMade(resultSet.getString("made"));
				pet.setAmount(resultSet.getInt("amount"));
				pet.setMoney(resultSet.getDouble("money"));
				pet.setPetBirthday(resultSet.getDate("petBirthday"));
				pet.setImage(resultSet.getString("image"));
				pet.setStatus(resultSet.getBoolean("status"));
				pet.setCategoryId(resultSet.getInt("categoryId"));
				pet.setCatalogId(resultSet.getInt("catalogId"));
				pets.add(pet);

			}
		} catch (Exception e) {
			e.printStackTrace();
			pets = null;
		} finally {
			ConnectDB.disconnect();
		}
		return pets;
	}

	public List<Pets> findAllByFilter(String category, String priceRange, int limit) {
		List<Pets> pets = new ArrayList<>();
		StringBuilder query = new StringBuilder("SELECT * FROM pets");

		boolean hasWhereClause = false;

		// Xử lý lọc theo danh mục
		if (!category.equals("all")) {
			query.append(" WHERE categoryId = ?");
			hasWhereClause = true;
		}

		// Xử lý lọc theo mức giá
		if (!priceRange.isEmpty()) {
			if (hasWhereClause) {
				query.append(" AND ");
			} else {
				query.append(" WHERE ");
				hasWhereClause = true;
			}
			query.append(buildPriceCondition(priceRange));
		}

		// Thêm điều kiện giới hạn
		query.append(" LIMIT ?");

		try {
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement(query.toString());
			int index = 1;

			// Thiết lập tham số cho danh mục
			if (!category.equals("all")) {
				preparedStatement.setInt(index++, getCategoryIdByName(category));
			}

			// Không cần thiết lập tham số cho mức giá vì điều kiện đã được xây dựng trong
			// buildPriceCondition

			// Thiết lập tham số cho số lượng hiển thị
			preparedStatement.setInt(index, limit);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Pets pet = new Pets();
				pet.setId(resultSet.getInt("id"));
				pet.setPetName(resultSet.getString("petName"));
				pet.setPetType(resultSet.getString("petType"));
				pet.setPetGender(resultSet.getString("petGender"));
				pet.setDescription(resultSet.getString("description"));
				pet.setDetail(resultSet.getString("detail"));
				pet.setMade(resultSet.getString("made"));
				pet.setAmount(resultSet.getInt("amount"));
				pet.setMoney(resultSet.getDouble("money"));
				pet.setPetBirthday(resultSet.getDate("petBirthday"));
				pet.setImage(resultSet.getString("image"));
				pet.setStatus(resultSet.getBoolean("status"));
				pet.setCategoryId(resultSet.getInt("categoryId"));
				pet.setCatalogId(resultSet.getInt("catalogId"));
				pets.add(pet);
			}
		} catch (Exception e) {
			e.printStackTrace();
			pets = null;
		} finally {
			ConnectDB.disconnect();
		}

		return pets;
	}

	private int getCategoryIdByName(String categoryName) {
		switch (categoryName) {
		case "dogs":
			return 1;
		case "cats":
			return 2;
		case "others":
			return 3;
		default:
			return -1; // hoặc ném ngoại lệ nếu cần thiết
		}
	}

	private String buildPriceCondition(String priceRange) {
		switch (priceRange) {
		case "below_2":
			return "money < 2.0"; // Đơn vị là triệu đồng
		case "2_3_5":
			return "money BETWEEN 2.0 AND 3.5";
		case "above_3_5":
			return "money > 3.5";
		default:
			return "";
		}
	}

	// ham lay ra danh sach pet con trong kho
	public List<Pets> findAllWithPositiveQuantity() {
		List<Pets> pets = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("SELECT * FROM pets WHERE amount > 0");
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Pets pet = new Pets();
				pet.setId(resultSet.getInt("id"));
				pet.setPetName(resultSet.getString("petName"));
				pet.setPetType(resultSet.getString("petType"));
				pet.setPetGender(resultSet.getString("petGender"));
				pet.setDescription(resultSet.getString("description"));
				pet.setDetail(resultSet.getString("detail"));
				pet.setMade(resultSet.getString("made"));
				pet.setAmount(resultSet.getInt("amount"));
				pet.setMoney(resultSet.getDouble("money"));
				pet.setPetBirthday(resultSet.getDate("petBirthday"));
				pet.setImage(resultSet.getString("image"));
				pet.setStatus(resultSet.getBoolean("status"));
				pet.setCategoryId(resultSet.getInt("categoryId"));
				pet.setCatalogId(resultSet.getInt("catalogId"));
				pets.add(pet);
			}
		} catch (Exception e) {
			e.printStackTrace();
			pets = null;
		} finally {
			ConnectDB.disconnect();
		}
		return pets;
	}

	// lay ra danh sach pet không xuất hiện trong đơn hàng 3 tháng qua nhưng trước
	// đó có
	public List<Pets> findInactivePetsInRange(Date startDate, Date endDate) {
		List<Pets> pets = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("SELECT p.* FROM pets p " + "JOIN orderdetails od ON p.id = od.petId "
							+ "JOIN orders o ON od.orderId = o.id " + "WHERE o.orderDate < ? AND o.orderDate > ? "
							+ "AND p.id NOT IN (SELECT od.petId FROM orderdetails od "
							+ "JOIN orders o ON od.orderId = o.id " + "WHERE o.orderDate >= ? AND o.orderDate <= ?) "
							+ "GROUP BY p.id");
			preparedStatement.setDate(1, (java.sql.Date) startDate);
			preparedStatement.setDate(2, (java.sql.Date) endDate);
			preparedStatement.setDate(3, (java.sql.Date) startDate);
			preparedStatement.setDate(4, (java.sql.Date) endDate);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Pets pet = new Pets();
				pet.setId(rs.getInt("id"));
				pet.setPetName(rs.getString("petName"));
				pet.setPetType(rs.getString("petType"));
				pet.setPetGender(rs.getString("petGender"));
				pet.setDescription(rs.getString("description"));
				pet.setDetail(rs.getString("detail"));
				pet.setMade(rs.getString("made"));
				pet.setAmount(rs.getInt("amount"));
				pet.setMoney(rs.getDouble("money"));
				pet.setPetBirthday(rs.getDate("petBirthday"));
				pet.setImage(rs.getString("image"));
				pet.setStatus(rs.getBoolean("status"));
				pet.setCategoryId(rs.getInt("categoryId"));
				pet.setCatalogId(rs.getInt("catalogId"));
				pets.add(pet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pets;
	}

	// ham lay danh sach tat ca pet con trong cua hang
	public List<Pets> findAllExist(boolean status) {
		List<Pets> pets = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("select * from pets where status = ? ");
			preparedStatement.setBoolean(1, status);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Pets pet = new Pets();
				pet.setId(resultSet.getInt("id"));
				pet.setPetName(resultSet.getString("petName"));
				pet.setPetType(resultSet.getString("petType"));
				pet.setPetGender(resultSet.getString("petGender"));
				pet.setDescription(resultSet.getString("description"));
				pet.setDetail(resultSet.getString("detail"));
				pet.setMade(resultSet.getString("made"));
				pet.setAmount(resultSet.getInt("amount"));
				pet.setMoney(resultSet.getDouble("money"));
				pet.setPetBirthday(resultSet.getDate("petBirthday"));
				pet.setImage(resultSet.getString("image"));
				pet.setStatus(resultSet.getBoolean("status"));
				pet.setCategoryId(resultSet.getInt("categoryId"));
				pet.setCatalogId(resultSet.getInt("catalogId"));
				pets.add(pet);

			}
		} catch (Exception e) {
			e.printStackTrace();
			pets = null;
		} finally {
			ConnectDB.disconnect();
		}
		return pets;
	}

// tim kiem theo ten hien 5 pet
	public List<Pets> findByName(String keyword) {
		List<Pets> pets = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("select * from pets where petName like ? limit 5");
			preparedStatement.setString(1, "%" + keyword + "%");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Pets pet = new Pets();
				pet.setId(resultSet.getInt("id"));
				pet.setPetName(resultSet.getString("petName"));
				pet.setPetType(resultSet.getString("petType"));
				pet.setPetGender(resultSet.getString("petGender"));
				pet.setDescription(resultSet.getString("description"));
				pet.setDetail(resultSet.getString("detail"));
				pet.setMade(resultSet.getString("made"));
				pet.setAmount(resultSet.getInt("amount"));
				pet.setMoney(resultSet.getDouble("money"));
				pet.setPetBirthday(resultSet.getDate("petBirthday"));
				pet.setImage(resultSet.getString("image"));
				pet.setStatus(resultSet.getBoolean("status"));
				pet.setCategoryId(resultSet.getInt("categoryId"));
				pet.setCatalogId(resultSet.getInt("catalogId"));
				pets.add(pet);

			}
		} catch (Exception e) {
			e.printStackTrace();
			pets = null;
		} finally {
			ConnectDB.disconnect();
		}
		return pets;
	}

	public List<Pets> findByKeyword(String keyword) {
		List<Pets> pets = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("SELECT p.*, c.name AS categoryName, cat.name AS catalogName " + "FROM pets p "
							+ "JOIN categorys c ON p.categoryId = c.id " + "JOIN catalogs cat ON p.catalogId = cat.id "
							+ "WHERE p.petName LIKE ? OR c.name LIKE ? OR cat.name LIKE ?");
			String searchPattern = "%" + keyword + "%";
			preparedStatement.setString(1, searchPattern);
			preparedStatement.setString(2, searchPattern);
			preparedStatement.setString(3, searchPattern);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Pets pet = new Pets();
				pet.setId(resultSet.getInt("id"));
				pet.setPetName(resultSet.getString("petName"));
				pet.setPetType(resultSet.getString("petType"));
				pet.setPetGender(resultSet.getString("petGender"));
				pet.setDescription(resultSet.getString("description"));
				pet.setDetail(resultSet.getString("detail"));
				pet.setMade(resultSet.getString("made"));
				pet.setAmount(resultSet.getInt("amount"));
				pet.setMoney(resultSet.getDouble("money"));
				pet.setPetBirthday(resultSet.getDate("petBirthday"));
				pet.setImage(resultSet.getString("image"));
				pet.setStatus(resultSet.getBoolean("status"));
				pet.setCategoryId(resultSet.getInt("categoryId"));
				pet.setCatalogId(resultSet.getInt("catalogId"));
				pets.add(pet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pets;
	}

	// ham tim ra pets dua vao id
	public Pets findPetById(int id) {
		Pets pet = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("select * from pets where id = ? ");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				pet = new Pets();
				pet.setId(resultSet.getInt("id"));
				pet.setPetName(resultSet.getString("petName"));
				pet.setPetType(resultSet.getString("petType"));
				pet.setPetGender(resultSet.getString("petGender"));
				pet.setDescription(resultSet.getString("description"));
				pet.setDetail(resultSet.getString("detail"));
				pet.setMade(resultSet.getString("made"));
				pet.setAmount(resultSet.getInt("amount"));
				pet.setMoney(resultSet.getDouble("money"));
				pet.setPetBirthday(resultSet.getDate("petBirthday"));
				pet.setImage(resultSet.getString("image"));
				pet.setStatus(resultSet.getBoolean("status"));
				pet.setCategoryId(resultSet.getInt("categoryId"));
				pet.setCatalogId(resultSet.getInt("catalogId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			pet = null;
		} finally {
			ConnectDB.disconnect();
		}
		return pet;
	}

	public List<Pets> findTop10() {
		List<Pets> pets = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("select * from pets order by id desc limit 10");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Pets pet = new Pets();
				pet.setId(resultSet.getInt("id"));
				pet.setPetName(resultSet.getString("petName"));
				pet.setPetGender(resultSet.getString("petGender"));
				pet.setDescription(resultSet.getString("description"));
				pet.setDetail(resultSet.getString("detail"));
				pet.setMade(resultSet.getString("made"));
				pet.setAmount(resultSet.getInt("amount"));
				pet.setMoney(resultSet.getDouble("money"));
				pet.setPetBirthday(resultSet.getDate("petBirthday"));
				pet.setImage(resultSet.getString("image"));
				pet.setStatus(resultSet.getBoolean("status"));
				pet.setCategoryId(resultSet.getInt("categoryId"));
				pet.setCatalogId(resultSet.getInt("catalogId"));
				pets.add(pet);

			}
		} catch (Exception e) {
			e.printStackTrace();
			pets = null;
		} finally {
			ConnectDB.disconnect();
		}
		return pets;
	}

	public int checkExist(int petID, List<Pets> pets) {

		for (int i = 0; i < pets.size(); i++) {
			if (petID == pets.get(i).getId()) {
				return i;
			}
		}
		return -1;
	}

	// ham tra ve danh sach cho cua vao id category
	public static List<Pets> findAllByCategory(int categoryId) {
		List<Pets> pets = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("select * from pets where categoryId = ?");
			preparedStatement.setInt(1, categoryId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Pets pet = new Pets();
				pet.setId(resultSet.getInt("id"));
				pet.setPetName(resultSet.getString("petName"));
				pet.setPetGender(resultSet.getString("petGender"));
				pet.setDescription(resultSet.getString("description"));
				pet.setDetail(resultSet.getString("detail"));
				pet.setMade(resultSet.getString("made"));
				pet.setAmount(resultSet.getInt("amount"));
				pet.setMoney(resultSet.getDouble("money"));
				pet.setPetBirthday(resultSet.getDate("petBirthday"));
				pet.setImage(resultSet.getString("image"));
				pet.setStatus(resultSet.getBoolean("status"));
				pet.setCategoryId(resultSet.getInt("categoryId"));
				pet.setCatalogId(resultSet.getInt("catalogId"));
				pets.add(pet);
			}
		} catch (Exception e) {
			e.printStackTrace();
			pets = null;
		} finally {
			ConnectDB.disconnect();
		}
		return pets;
	}

	public List<Pets> findAllByCatalog(int categoryId, int catalogId) {
		List<Pets> pets = new ArrayList<Pets>();
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("select * from pets where catalogId = ? and categoryId = ?");
			preparedStatement.setInt(1, catalogId);
			preparedStatement.setInt(2, categoryId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Pets pet = new Pets();
				pet.setId(resultSet.getInt("id"));
				pet.setPetName(resultSet.getString("petName"));
				pet.setPetGender(resultSet.getString("petGender"));
				pet.setDescription(resultSet.getString("description"));
				pet.setDetail(resultSet.getString("detail"));
				pet.setMade(resultSet.getString("made"));
				pet.setAmount(resultSet.getInt("amount"));
				pet.setMoney(resultSet.getDouble("money"));
				pet.setPetBirthday(resultSet.getDate("petBirthday"));
				pet.setImage(resultSet.getString("image"));
				pet.setStatus(resultSet.getBoolean("status"));
				pet.setCategoryId(resultSet.getInt("categoryId"));
				pet.setCatalogId(resultSet.getInt("catalogId"));
				pets.add(pet);
			}
		} catch (Exception e) {
			e.printStackTrace();
			pets = null;
		} finally {
			ConnectDB.disconnect();
		}
		return pets;
	}

	// ham tuoi tho cho pet dua vao ngay sinh
	public String ageByPet(String date) {
		int age = 0;
		String result = "";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate birthdateLocal = LocalDate.parse(date, formatter);
		LocalDate currentDateLocal = LocalDate.now();
		age = (int) ChronoUnit.MONTHS.between(birthdateLocal, currentDateLocal);
		int years = age / 12;
		int months = age % 12;
		if (years == 0) {
			result = months + " tháng";
		} else {
			result = years + " năm " + months + " tháng";
		}
		return result;
	}

	public boolean create(Pets pet) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement(
					"insert into pets(petName, petType,petGender,description, detail, made, amount, money, petBirthday, image,status, categoryId, catalogId) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, pet.getPetName());
			preparedStatement.setString(2, pet.getPetType());
			preparedStatement.setString(3, pet.getPetGender());
			preparedStatement.setString(4, pet.getDescription());
			preparedStatement.setString(5, pet.getDetail());
			preparedStatement.setString(6, pet.getMade());
			preparedStatement.setInt(7, pet.getAmount());
			preparedStatement.setDouble(8, pet.getMoney());
			preparedStatement.setDate(9, new java.sql.Date(pet.getPetBirthday().getDate()));
			preparedStatement.setString(10, pet.getImage());
			preparedStatement.setBoolean(11, pet.isStatus());
			preparedStatement.setInt(12, pet.getCategoryId());
			preparedStatement.setInt(13, pet.getCatalogId());
			result = preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = false;
		} finally {
			ConnectDB.disconnect();
		}
		return result;
	}

	// ham update
	public boolean update(Pets pet) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement(
					"update pets set petName =?, petType=?,petGender=?,description=?, detail=?, made=?, amount=?, money=?, petBirthday=?, image=?,status=?, categoryId=?, catalogId=? where id = ? ");
			preparedStatement.setString(1, pet.getPetName());
			preparedStatement.setString(2, pet.getPetType());
			preparedStatement.setString(3, pet.getPetGender());
			preparedStatement.setString(4, pet.getDescription());
			preparedStatement.setString(5, pet.getDetail());
			preparedStatement.setString(6, pet.getMade());
			preparedStatement.setInt(7, pet.getAmount());
			preparedStatement.setDouble(8, pet.getMoney());
			preparedStatement.setDate(9, new java.sql.Date(pet.getPetBirthday().getTime()));
			preparedStatement.setString(10, pet.getImage());
			preparedStatement.setBoolean(11, pet.isStatus());
			preparedStatement.setInt(12, pet.getCategoryId());
			preparedStatement.setInt(13, pet.getCatalogId());
			preparedStatement.setInt(14, pet.getId());
			result = preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			ConnectDB.disconnect();
		}
		return result;
	}

	// ham xoa user
	public boolean delete(int id) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("delete from pets where id = ? ");
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			ConnectDB.disconnect();
		}
		return result;
	}

	public Pets lastPets() {
		Pets pet = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("select * from pets order by id desc limit 1");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				pet = new Pets();
				pet.setId(resultSet.getInt("id"));
				pet.setPetName(resultSet.getString("petName"));
				pet.setPetGender(resultSet.getString("petGender"));
				pet.setDescription(resultSet.getString("description"));
				pet.setDetail(resultSet.getString("detail"));
				pet.setMade(resultSet.getString("made"));
				pet.setAmount(resultSet.getInt("amount"));
				pet.setMoney(resultSet.getDouble("money"));
				pet.setPetBirthday(resultSet.getDate("petBirthday"));
				pet.setImage(resultSet.getString("image"));
				pet.setStatus(resultSet.getBoolean("status"));
				pet.setCategoryId(resultSet.getInt("categoryId"));
				pet.setCatalogId(resultSet.getInt("catalogId"));

			}
		} catch (Exception e) {
			e.printStackTrace();
			pet = null;
			// TODO: handle exception
		} finally {
			ConnectDB.disconnect();
		}

		return pet;
	}

	public static void main(String[] args) {
		PetModel petModel = new PetModel();
//			System.out.println(petModel.findTop10());
//			System.out.println(petModel.findPetById(1));
// 		System.out.println(petModel.findAllByCategory(1));
//			System.out.println(userModel.checkLogin("acc2", "123"));
//			System.out.println(BCrypt.checkpw("123", "$2a$10$GQtaPy7y2Q3gsPhA.QlJueoo0wGjy.hNK5/U/GTqqxXMGEjtDnkRi"));
//			System.out.println(BCrypt.hashpw("123", BCrypt.gensalt()));
//			System.out.println(petModel.ageByPet("2003-12-08"));
//			System.out.println(petModel.findAllByCatalog(3, 16));
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//			System.out.println(petModel.create(new Pets(1,"test1", "Lớn","Nhỏ","Oke", "Ổn", "Việt Nam", 1, 1.1, new Date(), "pet.png", 3, 4)));
		System.out.println(petModel.findPaginatedByCategory(1,2,12));
	}
}
