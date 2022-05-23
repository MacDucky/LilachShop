package org.lilachshop.entities;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;


@Embeddable
public class CreditCard implements Serializable {
    protected CreditCard() {
    }

    public CreditCard(String creditCardNumber, LocalDate expirationDate, String cardHolderName, Long cardHolderID) {
        this.creditCardNumber = creditCardNumber;
        this.expirationDate = expirationDate;
        this.cardHolderName = cardHolderName;
        this.cardHolderID = cardHolderID;
    }


    private String creditCardNumber;

    @Basic(optional = false)

    private LocalDate expirationDate;

    @Basic(optional = false)
    private String cardHolderName;

    @Basic(optional = false)
    private Long cardHolderID;
    // todo: cvc??


    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public Long getCardHolderID() {
        return cardHolderID;
    }

    public void setCardHolderID(Long cardHolderID) {
        this.cardHolderID = cardHolderID;
    }
}
