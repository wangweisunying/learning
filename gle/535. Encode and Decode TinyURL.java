// // 535. Encode and Decode TinyURL
// // DescriptionHintsSubmissionsDiscussSolution
// // Note: This is a companion problem to the System Design problem: Design TinyURL.
// // TinyURL is a URL shortening service where you enter a URL 
// such as https://leetcode.com/problems/design-tinyurl and
//  it returns a short URL such as http://tinyurl.com/4e9iAk.

// // Design the encode and decode methods for the TinyURL service. 
// There is no restriction on how your encode/decode algorithm should work.
// You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.


public class Codec {

    // Encodes a URL to a shortened URL.
    int id = 1;
    Map<Integer , String > map = new HashMap();
    String base62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
    public String encode(String longUrl) {
        map.put(id , longUrl);
        String res = "";
        int tmpId = id;
        while(tmpId > 0){
            res += base62.charAt(tmpId % 62);
            tmpId /= 62;
        }
        ++id;
        return res;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        int res = 0 ;
        // 类似 stack 14（ab）算法
        for(int i = 0 ; i < shortUrl.length() ; i++){
            res += res * 62 + base62.indexOf(shortUrl.charAt(i));
        }
        return map.get(res);
    }
}

































public class Codec {
    Map<Integer, String> map = new HashMap<>();

    public String encode(String longUrl) {
        map.put(longUrl.hashCode(), longUrl);
        return "http://tinyurl.com/" + longUrl.hashCode();
    }

    public String decode(String shortUrl) {
        return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));

// The number of URLs that can be encoded is limited by the range of \text{int}int, since \text{hashCode}hashCode uses integer calculations.

// The average length of the encoded URL isn't directly related to the incoming \text{longURL}longURL length.

// The \text{hashCode()}hashCode() doesn't generate unique codes for different string. 
// This property of getting the same code for two different inputs is called collision.
//  Thus, as the number of encoded URLs increases, the probability of collisions increases, which leads to failure.

// The following figure demonstrates the mapping of different objects to the same hashcode 
// and the increasing probability of collisions with increasing number of objects.

// Encode_and_Decode_URLs

// Thus, it isn't necessary that the collisions start occuring only after a certain number of strings have been encoded, but they could occur way before the limit is even near to the \text{int}int.
//  This is similar to birthday paradox i.e. the probability of two people having the same birthday is nearly 50% if we consider only 23 people and 99.9% with just 70 people.

// Predicting the encoded URL isn't easy in this scheme.