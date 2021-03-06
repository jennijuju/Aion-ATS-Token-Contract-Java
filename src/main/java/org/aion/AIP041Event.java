package org.aion;

import avm.Address;
import avm.Blockchain;
import org.aion.avm.userlib.AionBuffer;
import org.aion.avm.userlib.AionUtilities;

import java.math.BigInteger;

public class AIP041Event {

    private static int BIGINTEGER_LENGTH = 32;

    protected static void AIP041AIPTokenCreated(BigInteger totalSupply, Address creator, String tokenName, int tokenGranularity, String tokenSymbol) {
        byte[] data = AionBuffer.allocate(Integer.BYTES + tokenSymbol.length())
                .putInt(tokenGranularity)
                .put(tokenSymbol.getBytes())
                .getArray();

        Blockchain.log("AIP041TokenCreated".getBytes(),
                AionUtilities.padLeft(totalSupply.toByteArray()),
                creator.toByteArray(),
                tokenName.getBytes(),
                data);
    }

    /**
     * Store byte[] sizes for collecting data
     *
     * @param operator
     * @param from
     * @param to
     * @param amount
     * @param holderData
     * @param operatorData
     */
    protected static void AIP041Sent(Address operator, Address from, Address to, BigInteger amount, byte[] holderData, byte[] operatorData) {

        if (holderData == null){
            holderData = new byte[0];
        }

        if (operatorData == null){
            operatorData = new byte[0];
        }

        byte[] data = AionBuffer.allocate(BIGINTEGER_LENGTH + Integer.BYTES + holderData.length + Integer.BYTES + operatorData.length)
                .put32ByteInt(amount)
                .putInt(holderData.length)
                .put(holderData)
                .putInt(operatorData.length)
                .put(operatorData)
                .getArray();

        Blockchain.log("AIP041Sent".getBytes(),
                operator.toByteArray(),
                from.toByteArray(),
                to.toByteArray(),
                data);

    }

    /**
     * Store byte[] sizes for collecting data
     *
     * @param operator
     * @param from
     * @param amount
     * @param holderData
     * @param operatorData
     */
    protected static void AIP041Burned(Address operator, Address from, BigInteger amount, byte[] holderData, byte[] operatorData) {

        if (holderData == null){
            holderData = new byte[0];
        }

        if (operatorData == null){
            operatorData = new byte[0];
        }

        byte[] data = AionBuffer.allocate(BIGINTEGER_LENGTH + Integer.BYTES + holderData.length + Integer.BYTES + operatorData.length)
                .put32ByteInt(amount)
                .putInt(holderData.length)
                .put(holderData)
                .putInt(operatorData.length)
                .put(operatorData)
                .getArray();

        Blockchain.log("AIP041Burned".getBytes(),
                operator.toByteArray(),
                from.toByteArray(),
                data);
    }

    protected static void AIP041AuthorizedOperator(Address operator, Address tokenHolder) {
        Blockchain.log("AIP041AuthorizedOperator".getBytes(),
                operator.toByteArray(),
                tokenHolder.toByteArray());
    }

    protected static void AIP041RevokedOperator(Address operator, Address tokenHolder) {
        Blockchain.log("AIP041RevokedOperator".getBytes(),
                operator.toByteArray(),
                tokenHolder.toByteArray());
    }

    protected static void AIP041Minted(Address issuer, Address to, BigInteger amount, byte[] data, byte[] issuerData) {

        if (data == null){
            data = new byte[0];
        }

        if (issuerData == null){
            issuerData = new byte[0];
        }

        byte[] eventData = AionBuffer.allocate(Integer.BYTES + data.length + Integer.BYTES + issuerData.length)
                .putInt(data.length)
                .put(data)
                .putInt(issuerData.length)
                .put(issuerData)
                .getArray();

        Blockchain.log("AIP041Minted".getBytes(),
                issuer.toByteArray(),
                to.toByteArray(),
                AionUtilities.padLeft(amount.toByteArray()),
                eventData);
    }
}
