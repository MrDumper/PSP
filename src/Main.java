public class Main {
    public static void main(String[] args) {
        Website website = new Website();
        ChemistryWebsite chemistryWebsite = new ChemistryWebsite();
        MetamathematicsWebsite metamathematicsWebsite = new MetamathematicsWebsite();
        Informational informational = new Informational();
        SocialNetwork socialNetwork = new SocialNetwork();

        // Вызов методов из интерфейсов и классов
        website.print();
        website.yourMethod();

        chemistryWebsite.print();
        chemistryWebsite.yourMethod();

        metamathematicsWebsite.print();
        metamathematicsWebsite.yourMethod();

        informational.print();
        informational.yourMethod();

        socialNetwork.print();
        socialNetwork.yourMethod();
    }
}