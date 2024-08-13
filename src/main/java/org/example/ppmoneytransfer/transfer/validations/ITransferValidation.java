package org.example.ppmoneytransfer.transfer.validations;

import org.example.ppmoneytransfer.transfer.vo.TransferRequestVO;

public interface ITransferValidation {
    void validate(TransferRequestVO request);
}