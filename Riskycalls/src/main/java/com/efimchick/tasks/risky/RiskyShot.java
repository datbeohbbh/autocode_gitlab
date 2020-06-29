package com.efimchick.tasks.risky;

import com.efimchick.tasks.risky.util.RussianRoulette;

import java.io.IOException;
import java.io.FileNotFoundException;

public class RiskyShot {

    final int input;
    final RussianRoulette roulette;

    public RiskyShot(final int input,
                     final RussianRoulette roulette) {
        this.input = input;
        this.roulette = roulette;
    }

    void checkIllegalArgumentException(Exception ex) throws FileNotFoundException,IOException {
        if(ex instanceof FileNotFoundException){
            throw new FileNotFoundException("1");
        } else if(ex instanceof IOException){
            throw new IOException("2");
        }
    }

    void checkUnsupportedOperationException(Exception ex) throws UnsupportedOperationException{
        if(ex instanceof UnsupportedOperationException){
            throw new UnsupportedOperationException("5");
        }
    }

    void checkRuntimeException(Exception ex) throws RuntimeException{
        if(ex instanceof RuntimeException){
            throw new RuntimeException();
        }
    }

    void checkArithmeticorNumberFormatException(Exception ex) throws NumberFormatException,ArithmeticException{
        if(ex instanceof ArithmeticException){
            throw new ArithmeticException("3");
        } else if(ex instanceof NumberFormatException){
            throw new NumberFormatException("4");
        }
    }

    public void handleShot() /*You may not add "throws" here*/ {
        // handle method call
        //roulette.shot(input);
        try{
            roulette.shot(input);
        } catch(Exception ex){
            try{
                checkIllegalArgumentException(ex);
            } catch (FileNotFoundException e){
                throw new IllegalArgumentException("File is missing",e);
            } catch(IOException e){
                throw new IllegalArgumentException("File error",e);
            }
            // 1 2
            try{
                checkArithmeticorNumberFormatException(ex);
            } catch (ArithmeticException e){
                for(int i = 1;i <= 2;++i){
                    try{
                        roulette.shot(input);
                    } catch (Exception ee){
                        continue;
                    }
                }
                return ;
            } catch (NumberFormatException e){
                try{
                    roulette.shot(input);
                } catch (Exception ee){
                    return;
                }
            }
            //3 4
            try{
                checkUnsupportedOperationException(ex);
            } catch (UnsupportedOperationException e){
                throw new UnsupportedOperationException("5");
            }
            // 5
            try{
                checkRuntimeException(ex);
            } catch(RuntimeException e){
                throw new RuntimeException(e);
            }
            // null
        }
    }
}
