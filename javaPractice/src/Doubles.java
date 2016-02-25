public class Doubles {

    public Double tryParse(String s) {
        if (s == null || s.length() == 0) return null;
        State status = State.INIT;
        int i = 0;
        while (i < s.length()) {
            char symbol = s.charAt(i);
            status = status.getNextState(symbol, status);
            if (status == null) return null;
            i++;
        }
        return status.getResult(status);
    }

    private enum State {

        INIT {
            @Override
            State getNextState(char symbol, State status) {
                if (isSign(symbol)) {
                    if (symbol == '-') {
                        sign = -1;
                    }
                    return SIGN;
                }
                if (isDigit(symbol)) {
                    result += Character.digit(symbol, 10);
                    return INT_NUM;
                }
                if (isPoint(symbol)) return POINT;
                return null;
            }
        },

        SIGN {
            State getNextState(char symbol, State status) {
                if (isDigit(symbol)) {
                    result = sign * Character.digit(symbol, 10);
                    return INT_NUM;
                }
                if (isPoint(symbol)) return POINT;
                return null;
            }
        },

        INT_NUM {
            @Override
            State getNextState(char symbol, State status) {
                if (isDigit(symbol)) {
                    if (status == SIGN || status == INIT) {
                        result *= Character.digit(symbol, 10);
                    } else {
                        result = result * 10 + Character.digit(symbol, 10);
                    }
                    return INT_NUM;
                }
                if (isPoint(symbol)) return POINT;
                if (isExp(symbol)) return EXP;
                return null;
            }
        },

        POINT {
            @Override
            State getNextState(char symbol, State status) {
                if (isDigit(symbol)) {
                    double temp = 0.1 * Character.digit(symbol, 10);
                    if (result < 0) {
                        result -= temp;
                    } else {
                        result += temp;
                    }
                    return DEC_NUM;
                }
                if (isExp(symbol) && result != 0) return EXP;
                return null;
            }
        },

        DEC_NUM {
            @Override
            State getNextState(char symbol, State status) {
                if (isDigit(symbol)) {
                    double temp = Math.pow(10, degree) * Character.digit(symbol, 10);
                    if (result < 0) {
                        result -= temp;
                    } else {
                        result += temp;
                    }
                    degree--;
                    return DEC_NUM;
                }
                if (isExp(symbol)) return EXP;
                return null;
            }
        },

        EXP {
            @Override
            State getNextState(char symbol, State status) {
                if (isDigit(symbol)) {
                    expNumber = Character.digit(symbol, 10);
                    sign = 1;
                    return EXP_NUM;
                }
                if (isSign(symbol)) {
                    if (symbol == '+') {
                        sign = 1;
                    } else {
                        sign = -1;
                    }
                    return EXP_SIGN;
                }
                return null;
            }
        },

        EXP_SIGN {
            @Override
            State getNextState(char symbol, State status) {
                if (isDigit(symbol)) {
                    expNumber = Character.digit(symbol, 10);
                    return EXP_NUM;
                }
                return null;
            }
        },

        EXP_NUM {
            @Override
            State getNextState(char symbol, State status) {
                if (isDigit(symbol)) {
                    expNumber = expNumber * 10 + Character.digit(symbol, 10);
                    return EXP_NUM;
                }
                return null;
            }
        };

        private static int sign = 1;
        private static double result = 0;
        private static int degree = -2;
        private static int expNumber = 0;

        public double getResult(State status) {
            if (status == EXP_NUM) return result * Math.pow(10, sign * expNumber);
            else return result;
        }

        abstract State getNextState (char symbol, State status);

        private static boolean isDigit(char symbol) {
            return (symbol - '0' >= 0 && symbol - '0' <= 9);
        }

        private static boolean isSign(char symbol) {
            return (symbol == '+' || symbol == '-');
        }

        private static boolean isPoint(char symbol) {
            return symbol == '.';
        }

        private static boolean isExp(char symbol) {
            return (symbol == 'E' || symbol == 'e');
        }
    }
}
