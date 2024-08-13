package org.example.ppmoneytransfer.validations;

import org.example.ppmoneytransfer.vo.TransferRequestVO;

public interface ITransferValidation {
    void validate(TransferRequestVO request);
}