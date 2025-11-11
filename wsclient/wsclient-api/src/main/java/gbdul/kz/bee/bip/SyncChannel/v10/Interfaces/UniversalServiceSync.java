/**
 * UniversalServiceSync.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdul.kz.bee.bip.SyncChannel.v10.Interfaces;

public interface UniversalServiceSync extends java.rmi.Remote {

    /**
     * Метод отправки сообщения по синхронному каналу ШЭП
     */
    gbdul.kz.bee.bip.SyncChannel.v10.Types.Response.SyncSendMessageResponse sendMessage(gbdul.kz.bee.bip.SyncChannel.v10.Types.Request.SyncSendMessageRequest request) throws java.rmi.RemoteException;
}
