package json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.Authorization;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class TestConversionJson2Map {
    public void convertJSON2Map() {
        String jsonFromMS = "{\"ChallengeParameters\":{},\"AuthenticationResult\":{\"AccessToken\":\"eyJraWQiOiJ6M2pWR1lscDVsS2VHVzRXMkwrU3BtdjN4UzIwWGFCU1FvZFlvdWg1WkU4PSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiI2MWI5ZTI4NC1kMzVhLTQ0M2YtOTlhNy04MGY0OWE4YTI4NmQiLCJ0b2tlbl91c2UiOiJhY2Nlc3MiLCJzY29wZSI6ImF3cy5jb2duaXRvLnNpZ25pbi51c2VyLmFkbWluIiwiaXNzIjoiaHR0cHM6XC9cL2NvZ25pdG8taWRwLnVzLWVhc3QtMS5hbWF6b25hd3MuY29tXC91cy1lYXN0LTFfMEhkMUtvTTVhIiwiZXhwIjoxNTA1MDg5NDY5LCJpYXQiOjE1MDUwODU4NjksImp0aSI6ImI4MzY3ZjcwLTA5MDktNGI0Mi1hNDI2LWM2ZTYyY2M3ZTI0YSIsImNsaWVudF9pZCI6IjFlOHRlZm1sY2wyaDI5MHZuaDlyY2VmbnZyIiwidXNlcm5hbWUiOiIxMDI5NTc2NSJ9.jfGvw9FX5-XBcVVaY21wL7F7Y6KRzRdLweCybx819JdRES2f-m4qGeftAZdzoRuLDJ4QfbGqJdJ1V19MKcw070qdz_zd3JFMo3ev9-1yrzQ9vr9hQR6Utk7cBLs_AGQlXHt2wNimvW7mPj_yyyjLa5LTveX0W-hZ5ZG2FSuiAE6dTmmSZs-FEjwZRPTNbjDVKHHr8QRBFq43gg23PkRCAriLO02ymrVblKCx_kqKvYbvNed_RB0IeGZP_Ll27F34-xdZz6r_zAcgxvOqafa5wPAbOvgCubE_rWviCujhgIa4qe_nqkqMIJhm0B3xOkG6K7Ju2yOy0rNSOzYI5JzRaA\",\"ExpiresIn\":3600,\"TokenType\":\"Bearer\",\"RefreshToken\":\"eyJjdHkiOiJKV1QiLCJlbmMiOiJBMjU2R0NNIiwiYWxnIjoiUlNBLU9BRVAifQ.XHVc-4jvQzziSase9_kebv08iy9Ipi4WW-Ab4-VrEC3yPbzcKjd-pP_qbMhDENdnYofLBl_Safi7KEDSuDXNctflqwwLNW14mdLnV50JBSm9xTsd2XXN8L3O-yTSf20lYhMkr1r4SFSmLQWxO_er8yfJwEoeijP31qKNAvm2ld24f258jVcN2_pl845t1HFuuLLA0l4Q1KvVey1pcoB7rCCNE_YH6FN5Uh9cx8wPT7Y-VRzTZf5TrYUcm66vDadloQkztu4oj4-rcAFav0pHGMbxmA5Q5f8PTFHnTsShaWKa9axhBUtY5UNZHwlo5Mk88l1_tTJwgChPud6N_dZPDw.IumWp7H8XetEgUMF.0l5DHHyHKRzAPsc5U6RxL7DSQG1LC15ag0-PrqsT9ZCIq9LJK9GzDTgDLtYiUGKwmEnd4JyFNwr4Hs58QzqPQpO_SVpb15MO7zHgHAFwHochMlOClYUD6SFoU4vA4-_YK8yvz7kuIW6C-7TDgdxpyMZIAlpcCMSAyIqjAM7DkVxek6vNF2d8XqZI6mB88euvihBAeVUo94ut1fcRPZu-PhlFvb0zTwukQ0ScqUEaea4MFKevK_SIIdgefO22i1fXCBom530vkvqNzup34KUiN8qhuzN7pF_r6dNQAeBFzkdp7fYCOD_sK3pVsjQ6A91ZGTyNGoQ_umlcylljUEC0v5yVOSKAEL9waomujOIAN2Z9KmdYwe8ytnqlpAURKiP_3UwuEUld4ie2vduy_cvKAPuGbD1O_gVo7TXkQ8cdPDo6BBUAVL9hEsFslWzrR2NVXbiR27fsmj2IFke4ho8668pDyrL-P8QIjIRy3fPD4_7JfCR8xC4QIMMvFbilvTvhFHSDfYubV_K8xaa4xSoXZ2qxQdT8yIrD81yJZeGgcEai_ag-bmedxudG8YrTnAZTdU7wkdRSHSppAe6qouov3jwMMKkj55lkkGu4YUOqIt_ldutGecrEEhSI4N_4y4aNPbuWGdbhyqDGBF1JY0vgiGX1wFARUGGnlsDPeA-5FcnUqzm0kzWwlKmsGoiTLUi6uNzwWWOVzyht5T4fICyxp-BRTbSuK6lhmfeu796RItp6q6HzBSVHC0fC0sRJeHYiK6MEVEtDkIjY9R2p36raaxl7No5lV4L0LLFD0U3f3pizfT4BFM3jJ7JPad3InG3uJAlq2xOzGKC30Mo6AghO6xE8u2Z9P5ulewY2AI1b8-uQqFvhB-0Zd7vWpGqqkf7IMhXmwD-0PvCUr0sdDa3JhapnvGAzbVCpNhoZHPwOYns2wcvXOWrJH59icIgc18jHl4Nom-H3oPsALBpbnhA1TkVj5U-0N2yA2q8DopnKhsL5-1CMGgReMBUFMQb98Hu048gMmPY7iF3NHfWrUMreeCNMhSm_459YeRrp0osTBen_iqkxzEuJd4WOzC6l2Tg6UiFnRbhCMwvQs-dzlQwvJkXtzCwfv58EzP4R9UV1XFT2FufESkIfOhyM8ILwKKv3dilyrNs.hXpBG-_svaWFcmQvcxHuVA\",\"IdToken\":\"eyJraWQiOiJKQWJRaHNlTTVldFRBMk1vMlpoNGs5Mlp2ZmFHaU5zWWtVQlFNbG1cL1Mybz0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiI2MWI5ZTI4NC1kMzVhLTQ0M2YtOTlhNy04MGY0OWE4YTI4NmQiLCJhdWQiOiIxZTh0ZWZtbGNsMmgyOTB2bmg5cmNlZm52ciIsImNvZ25pdG86Z3JvdXBzIjpbImFkbWluaXN0cmF0b3IiXSwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJ0b2tlbl91c2UiOiJpZCIsImF1dGhfdGltZSI6MTUwNTA4NTg2OSwiaXNzIjoiaHR0cHM6XC9cL2NvZ25pdG8taWRwLnVzLWVhc3QtMS5hbWF6b25hd3MuY29tXC91cy1lYXN0LTFfMEhkMUtvTTVhIiwiY29nbml0bzp1c2VybmFtZSI6IjEwMjk1NzY1IiwiZXhwIjoxNTA1MDg5NDY5LCJpYXQiOjE1MDUwODU4NjksImVtYWlsIjoiam9uYXRoYW4udEB4dHJhLmNvLm56In0.N2vUTeKskoSGGnZNNQkoJrjqQSTwCRsAyoVzU8HoSESciE97PbHu_rsDBHdlIvExQgZOJUDH2fi2ZTVXL0qw_dw_1oy5PY0ss6iF9Ya8vNvwJ4QIg0nGLJEEWO6y9h81bUiy8WYOyC0uCy4FKPBzxu-VjO_ZkJwQZLlhkx56cPXhQI9JFbwQDeyxJDF9duOGiZkWYmiG842TeYpaNIiW46KtQ8kiZLGre9qUdPjQv9S-Ch9Cm8bDqVSunAVtTityUJMGJuxhM78hPAujGdeNfMX0qEx37MpKbQtkb_xJZaT1c0IXnu1tneqtGVxZtaZdQ4mUlBmdzamQaWhlSXvqyA\"}}";

        Gson gson = new Gson();
        Type authorization = new TypeToken<Map<String, Authorization>>() {
        }.getType();
        Map<String, Authorization> authorizationMap = gson.fromJson(jsonFromMS, authorization);

        Map<String, Object> retMap = new Gson().fromJson(jsonFromMS, new TypeToken<HashMap<String, Object>>() {
        }.getType());

        Map<String, Object> map = new Gson().fromJson(jsonFromMS,
                new TypeToken<HashMap<String, Authorization>>() {
                }.getType());

        System.out.println("Printing Map object: " + authorizationMap.values().toString());

        authorizationMap.keySet().iterator().forEachRemaining(System.out::println);

        System.out.println("Printing ret Map object this is bringing the values: " + retMap.values().toString());

        map.keySet().iterator().forEachRemaining(System.out::println);

        System.out.println(retMap.get("IdToken"));
    }

    public void convertJSONtoMapStructure() {
        String jsonFromMS = "{\"ChallengeParameters\":{},\"AuthenticationResult\":{\"AccessToken\":\"eyJraWQiOiJ6M2pWR1lscDVsS2VHVzRXMkwrU3BtdjN4UzIwWGFCU1FvZFlvdWg1WkU4PSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiI2MWI5ZTI4NC1kMzVhLTQ0M2YtOTlhNy04MGY0OWE4YTI4NmQiLCJ0b2tlbl91c2UiOiJhY2Nlc3MiLCJzY29wZSI6ImF3cy5jb2duaXRvLnNpZ25pbi51c2VyLmFkbWluIiwiaXNzIjoiaHR0cHM6XC9cL2NvZ25pdG8taWRwLnVzLWVhc3QtMS5hbWF6b25hd3MuY29tXC91cy1lYXN0LTFfMEhkMUtvTTVhIiwiZXhwIjoxNTA1MDg5NDY5LCJpYXQiOjE1MDUwODU4NjksImp0aSI6ImI4MzY3ZjcwLTA5MDktNGI0Mi1hNDI2LWM2ZTYyY2M3ZTI0YSIsImNsaWVudF9pZCI6IjFlOHRlZm1sY2wyaDI5MHZuaDlyY2VmbnZyIiwidXNlcm5hbWUiOiIxMDI5NTc2NSJ9.jfGvw9FX5-XBcVVaY21wL7F7Y6KRzRdLweCybx819JdRES2f-m4qGeftAZdzoRuLDJ4QfbGqJdJ1V19MKcw070qdz_zd3JFMo3ev9-1yrzQ9vr9hQR6Utk7cBLs_AGQlXHt2wNimvW7mPj_yyyjLa5LTveX0W-hZ5ZG2FSuiAE6dTmmSZs-FEjwZRPTNbjDVKHHr8QRBFq43gg23PkRCAriLO02ymrVblKCx_kqKvYbvNed_RB0IeGZP_Ll27F34-xdZz6r_zAcgxvOqafa5wPAbOvgCubE_rWviCujhgIa4qe_nqkqMIJhm0B3xOkG6K7Ju2yOy0rNSOzYI5JzRaA\",\"ExpiresIn\":3600,\"TokenType\":\"Bearer\",\"RefreshToken\":\"eyJjdHkiOiJKV1QiLCJlbmMiOiJBMjU2R0NNIiwiYWxnIjoiUlNBLU9BRVAifQ.XHVc-4jvQzziSase9_kebv08iy9Ipi4WW-Ab4-VrEC3yPbzcKjd-pP_qbMhDENdnYofLBl_Safi7KEDSuDXNctflqwwLNW14mdLnV50JBSm9xTsd2XXN8L3O-yTSf20lYhMkr1r4SFSmLQWxO_er8yfJwEoeijP31qKNAvm2ld24f258jVcN2_pl845t1HFuuLLA0l4Q1KvVey1pcoB7rCCNE_YH6FN5Uh9cx8wPT7Y-VRzTZf5TrYUcm66vDadloQkztu4oj4-rcAFav0pHGMbxmA5Q5f8PTFHnTsShaWKa9axhBUtY5UNZHwlo5Mk88l1_tTJwgChPud6N_dZPDw.IumWp7H8XetEgUMF.0l5DHHyHKRzAPsc5U6RxL7DSQG1LC15ag0-PrqsT9ZCIq9LJK9GzDTgDLtYiUGKwmEnd4JyFNwr4Hs58QzqPQpO_SVpb15MO7zHgHAFwHochMlOClYUD6SFoU4vA4-_YK8yvz7kuIW6C-7TDgdxpyMZIAlpcCMSAyIqjAM7DkVxek6vNF2d8XqZI6mB88euvihBAeVUo94ut1fcRPZu-PhlFvb0zTwukQ0ScqUEaea4MFKevK_SIIdgefO22i1fXCBom530vkvqNzup34KUiN8qhuzN7pF_r6dNQAeBFzkdp7fYCOD_sK3pVsjQ6A91ZGTyNGoQ_umlcylljUEC0v5yVOSKAEL9waomujOIAN2Z9KmdYwe8ytnqlpAURKiP_3UwuEUld4ie2vduy_cvKAPuGbD1O_gVo7TXkQ8cdPDo6BBUAVL9hEsFslWzrR2NVXbiR27fsmj2IFke4ho8668pDyrL-P8QIjIRy3fPD4_7JfCR8xC4QIMMvFbilvTvhFHSDfYubV_K8xaa4xSoXZ2qxQdT8yIrD81yJZeGgcEai_ag-bmedxudG8YrTnAZTdU7wkdRSHSppAe6qouov3jwMMKkj55lkkGu4YUOqIt_ldutGecrEEhSI4N_4y4aNPbuWGdbhyqDGBF1JY0vgiGX1wFARUGGnlsDPeA-5FcnUqzm0kzWwlKmsGoiTLUi6uNzwWWOVzyht5T4fICyxp-BRTbSuK6lhmfeu796RItp6q6HzBSVHC0fC0sRJeHYiK6MEVEtDkIjY9R2p36raaxl7No5lV4L0LLFD0U3f3pizfT4BFM3jJ7JPad3InG3uJAlq2xOzGKC30Mo6AghO6xE8u2Z9P5ulewY2AI1b8-uQqFvhB-0Zd7vWpGqqkf7IMhXmwD-0PvCUr0sdDa3JhapnvGAzbVCpNhoZHPwOYns2wcvXOWrJH59icIgc18jHl4Nom-H3oPsALBpbnhA1TkVj5U-0N2yA2q8DopnKhsL5-1CMGgReMBUFMQb98Hu048gMmPY7iF3NHfWrUMreeCNMhSm_459YeRrp0osTBen_iqkxzEuJd4WOzC6l2Tg6UiFnRbhCMwvQs-dzlQwvJkXtzCwfv58EzP4R9UV1XFT2FufESkIfOhyM8ILwKKv3dilyrNs.hXpBG-_svaWFcmQvcxHuVA\",\"IdToken\":\"eyJraWQiOiJKQWJRaHNlTTVldFRBMk1vMlpoNGs5Mlp2ZmFHaU5zWWtVQlFNbG1cL1Mybz0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiI2MWI5ZTI4NC1kMzVhLTQ0M2YtOTlhNy04MGY0OWE4YTI4NmQiLCJhdWQiOiIxZTh0ZWZtbGNsMmgyOTB2bmg5cmNlZm52ciIsImNvZ25pdG86Z3JvdXBzIjpbImFkbWluaXN0cmF0b3IiXSwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJ0b2tlbl91c2UiOiJpZCIsImF1dGhfdGltZSI6MTUwNTA4NTg2OSwiaXNzIjoiaHR0cHM6XC9cL2NvZ25pdG8taWRwLnVzLWVhc3QtMS5hbWF6b25hd3MuY29tXC91cy1lYXN0LTFfMEhkMUtvTTVhIiwiY29nbml0bzp1c2VybmFtZSI6IjEwMjk1NzY1IiwiZXhwIjoxNTA1MDg5NDY5LCJpYXQiOjE1MDUwODU4NjksImVtYWlsIjoiam9uYXRoYW4udEB4dHJhLmNvLm56In0.N2vUTeKskoSGGnZNNQkoJrjqQSTwCRsAyoVzU8HoSESciE97PbHu_rsDBHdlIvExQgZOJUDH2fi2ZTVXL0qw_dw_1oy5PY0ss6iF9Ya8vNvwJ4QIg0nGLJEEWO6y9h81bUiy8WYOyC0uCy4FKPBzxu-VjO_ZkJwQZLlhkx56cPXhQI9JFbwQDeyxJDF9duOGiZkWYmiG842TeYpaNIiW46KtQ8kiZLGre9qUdPjQv9S-Ch9Cm8bDqVSunAVtTityUJMGJuxhM78hPAujGdeNfMX0qEx37MpKbQtkb_xJZaT1c0IXnu1tneqtGVxZtaZdQ4mUlBmdzamQaWhlSXvqyA\"}}";

        Gson gson = new Gson();

        Type authorization = new TypeToken<Map<String, Authorization>>() {
        }.getType();

        Map<String, Authorization> authorizationMap = gson.fromJson(jsonFromMS, authorization);

        Map<String, Object> retMap = new Gson().fromJson(jsonFromMS, new TypeToken<HashMap<String, Object>>() {
        }.getType());

        Map<String, Object> map = new Gson().fromJson(jsonFromMS, new TypeToken<HashMap<String, Authorization>>() {
        }.getType());

        System.out.println("Printing Map object: " + authorizationMap.values().toString());
        authorizationMap.keySet().iterator().forEachRemaining(System.out::println);
        System.out.println("Printing ret Map object this is bringing the values: " + retMap.values().toString());
        map.keySet().iterator().forEachRemaining(System.out::println);
        System.out.println(retMap.get("IdToken"));
    }
}
