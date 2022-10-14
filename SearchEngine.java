import java.io.IOException; 
import java.net.URI; 
import java.util.ArrayList;

class Handler implements URLHandler { 
    ArrayList<String> words = new ArrayList<String>();

    public String handleRequest(URI url) {
        System.out.println("Path: " + url.getPath());
        if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("input")) {
                words.add(0, parameters[1]);
                return parameters[1] + " is added";
            }
        }
        else if(url.getPath().contains("/search")){
            String[] parameters = url.getQuery().split("=");
            ArrayList<String> chosenWords = new ArrayList<String>();
            if (parameters[0].equals("input")){
                for(int i = 0; i < words.size(); i++){
                    if(words.get(i).contains(parameters[1])){
                        chosenWords.add(0, words.get(i));
                    }
                }
            }
            return chosenWords.toString();
        }
         else if (url.getPath().equals("/")) {
            return "Search or add a word";
         }
        else{
            return "404 Not Found!";
        }
        return "";
    }
}
class SearchEngine{
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("You need to put a port number. (A number between 1024 to 49151)");
            return;
        }
        int port = Integer.parseInt(args[0]);
        Server.start(port, new Handler());
    }
}


