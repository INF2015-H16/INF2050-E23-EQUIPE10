/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculetatcomptes;

/**
 *
 * @author seif saidi
 */
public class GestionErreurs {

    public static int surfaceTriangle(int cdClient) throws ClassExceptions {
        if (cdClient < 0) {
            throw new ClassExceptions("neg");
        }
        return cdClient;
    }

}
