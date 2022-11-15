package vttp.nusiss.arian.MiniProject2.repository;


public interface Queries {
    public static final String SQL_FIND_EXISTING_USER="SELECT * FROM user WHERE username LIKE ?";
    public static final String SQL_FIND_EXISTING_EMAIL="SELECT * FROM user WHERE email LIKE ?";
    public static final String SQL_FIND_ROLE_NAME="SELECT * FROM roles WHERE name LIKE ?";
    public static final String SQL_INSERT_USER="INSERT INTO user(username, password, email,role) VALUES(?,?,?,?)";
    public static final String SQL_FIND_GYM_BY_ID = "SELECT * FROM gym WHERE id=?";
    public static final String SQL_SELECT_ALL_GYMS = "SELECT * FROM gym";
    public static final String SQL_SELECT_PACKAGES_BY_USERID = "SELECT * FROM package LEFT JOIN gym ON gym.id=package.gym_id WHERE user_id = ?";
    public static final String SQL_INSERT_SUBPACKAGE = "INSERT INTO package(uuid, gym_id, user_id, entry_passes, expiry_date, expired) VALUES(?,?,?,?,?,?)";
    public static final String SQL_UPDATE_SUBPACKAGE ="UPDATE package SET entry_passes=?, expiry_date=? WHERE uuid=?";
    public static final String SQL_SELECT_SUBPACKAGE ="SELECT * FROM package LEFT JOIN gym ON gym.id=package.gym_id WHERE uuid=?";
    public static final String SQL_USE_PACKAGE ="UPDATE package SET entry_passes=entry_passes-1 WHERE uuid=?";
    public static final String SQL_DELETE_PACKAGE = "DELETE FROM package WHERE uuid=?";
}
