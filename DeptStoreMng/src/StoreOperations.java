public interface StoreOperations {
        void addEmployee(String name, int id, String position, double salary);
        void removeEmployee(int id);
        void showEmployeeManagementMenu();
    }

