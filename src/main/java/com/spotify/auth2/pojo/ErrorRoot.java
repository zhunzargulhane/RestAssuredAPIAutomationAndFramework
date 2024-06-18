package com.spotify.auth2.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorRoot {
    Error error;
    ErrorRoot(Error error) {
        this.error = error;
    }

    public static ErrorRootBuilder builder() {
        return new ErrorRootBuilder();
    }

    public static class ErrorRootBuilder {
        private Error error;

        ErrorRootBuilder() {
        }

        public ErrorRootBuilder error(Error error) {
            this.error = error;
            return this;
        }

        public ErrorRoot build() {
            return new ErrorRoot(error);
        }

        public String toString() {
            return "ErrorRoot.ErrorRootBuilder(error=" + this.error + ")";
        }
    }

}
