// **********************************************************************
//
// Copyright (c) 2003-2016 ZeroC, Inc. All rights reserved.
//
// **********************************************************************

using System;
using System.Collections.Generic;

public class PrinterI : Ice.Blobject
{
    private class ReadObjectCallback
    {
        public void invoke(Ice.Object obj)
        {
            this.obj = obj;
        }

        internal Ice.Object obj;
    }

    public override bool ice_invoke(byte[] inParams, out byte[] outParams, Ice.Current current)
    {
        outParams = null;

        Ice.Communicator communicator = current.adapter.getCommunicator();

        Ice.InputStream inStream = null;
        if(inParams.Length > 0)
        {
            inStream = new Ice.InputStream(communicator, inParams);
            inStream.startEncapsulation();
        }

        if(current.operation.Equals("printString"))
        {
            string message = inStream.readString();
            inStream.endEncapsulation();
            Console.WriteLine("Printing string `" + message + "'");
            return true;
        }
        else if(current.operation.Equals("printStringSequence"))
        {
            String[] seq = Demo.StringSeqHelper.read(inStream);
            inStream.endEncapsulation();
            Console.Write("Printing string sequence {");
            for(int i = 0; i < seq.Length; ++i)
            {
                if(i > 0)
                {
                    Console.Write(", ");
                }
                Console.Write("'" + seq[i] + "'");
            }
            Console.WriteLine("}");
            return true;
        }
        else if(current.operation.Equals("printDictionary"))
        {
            Dictionary<string, string> dict = Demo.StringDictHelper.read(inStream);
            inStream.endEncapsulation();
            Console.Write("Printing dictionary {");
            bool first = true;
            foreach(KeyValuePair<string, string> e in dict)
            {
                if(!first)
                {
                    Console.Write(", ");
                }
                first = false;
                Console.Write(e.Key + "=" + e.Value);
            }
            Console.WriteLine("}");
            return true;
        }
        else if(current.operation.Equals("printEnum"))
        {
            Demo.Color c = (Demo.Color)inStream.readEnum((int)Demo.Color.blue);
            inStream.endEncapsulation();
            Console.WriteLine("Printing enum " + c);
            return true;
        }
        else if(current.operation.Equals("printStruct"))
        {
            Demo.Structure s = new Demo.Structure();
            s.read__(inStream);
            inStream.endEncapsulation();
            Console.WriteLine("Printing struct: name=" + s.name + ", value=" + s.value);
            return true;
        }
        else if(current.operation.Equals("printStructSequence"))
        {
            Demo.Structure[] seq = Demo.StructureSeqHelper.read(inStream);
            inStream.endEncapsulation();
            Console.Write("Printing struct sequence: {");
            for(int i = 0; i < seq.Length; ++i)
            {
                if(i > 0)
                {
                    Console.Write(", ");
                }
                Console.Write(seq[i].name + "=" + seq[i].value);
            }
            Console.WriteLine("}");
            return true;
        }
        else if(current.operation.Equals("printClass"))
        {
            ReadObjectCallback cb = new ReadObjectCallback();
            inStream.readObject(cb.invoke);
            inStream.readPendingObjects();
            inStream.endEncapsulation();
            Demo.C c = cb.obj as Demo.C;
            Console.WriteLine("Printing class: s.name=" + c.s.name + ", s.value=" + c.s.value);
            return true;
        }
        else if(current.operation.Equals("getValues"))
        {
            Demo.C c = new Demo.C();
            c.s = new Demo.Structure();
            c.s.name = "green";
            c.s.value = Demo.Color.green;
            Ice.OutputStream outStream = new Ice.OutputStream(communicator);
            outStream.startEncapsulation();
            outStream.writeObject(c);
            outStream.writeString("hello");
            outStream.writePendingObjects();
            outStream.endEncapsulation();
            outParams = outStream.finished();
            return true;
        }
        else if(current.operation.Equals("throwPrintFailure"))
        {
            Console.WriteLine("Throwing PrintFailure");
            Demo.PrintFailure ex = new Demo.PrintFailure();
            ex.reason = "paper tray empty";
            Ice.OutputStream outStream = new Ice.OutputStream(communicator);
            outStream.startEncapsulation();
            outStream.writeException(ex);
            outStream.endEncapsulation();
            outParams = outStream.finished();
            return false;
        }
        else if(current.operation.Equals("shutdown"))
        {
            current.adapter.getCommunicator().shutdown();
            return true;
        }
        else
        {
            Ice.OperationNotExistException ex = new Ice.OperationNotExistException();
            ex.id = current.id;
            ex.facet = current.facet;
            ex.operation = current.operation;
            throw ex;
        }
    }
}
